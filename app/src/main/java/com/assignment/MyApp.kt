package com.assignment

import android.app.Application
import com.assignment.di.component.AppComponent
import com.assignment.di.component.DaggerAppComponent
import com.assignment.di.module.NetworkModule

open class MyApp: Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .networkModule(NetworkModule(applicationContext)) // create the module yourself
            .build()
    }
}