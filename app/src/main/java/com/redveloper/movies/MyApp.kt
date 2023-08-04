package com.redveloper.movies

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.redveloper.movies.di.ApplicationComponent
import com.redveloper.movies.di.DaggerApplicationComponent

class MyApp: MultiDexApplication() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()

        MultiDex.install(this)
    }
}