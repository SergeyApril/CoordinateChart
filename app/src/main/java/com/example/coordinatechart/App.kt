package com.example.coordinatechart

import android.app.Application
import android.content.Context
import com.example.coordinatechart.di.AppComponent
import com.example.coordinatechart.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initialize(applicationContext)
    }

    private fun initialize(context: Context) {
        appComponent = DaggerAppComponent.builder()
            .bindApplicationContext(context)
            .build()
    }
}