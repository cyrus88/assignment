package com.assignment.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.assignment.network.service.HomeService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule @Inject constructor(var context: Context) {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */

    @Provides
    fun application(): Context {
        return context
    }


    val isConnected : Boolean get(){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }


    @Provides
    fun provideUserDataApi(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Singleton
    fun provideRetrofitInterface(): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        val toBeAdded = 60 * 60 * 24 * 7

        // for caching
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            // add off line interceptor
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isConnected)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$toBeAdded"
                    ).build()
                chain.proceed(request)
            }
            // add on line interceptor
            .addNetworkInterceptor {

                val response: Response = it.proceed(it.request())
                val maxAge = 60 // read from cache for 60 seconds even if there is internet connection

                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            }
            .build()


        // create retrofit
        return Retrofit.Builder()
            .baseUrl("https://anime-facts-rest-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}