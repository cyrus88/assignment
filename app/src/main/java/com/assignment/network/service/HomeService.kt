package com.assignment.network.service

import com.assignment.network.model.BaseUsers
import retrofit2.Call
import retrofit2.http.GET

interface
HomeService {
    /**
     * Get the list of the anime from the API
     */
    @GET("v1")
    fun getNetworkDataList(): Call<BaseUsers>
}