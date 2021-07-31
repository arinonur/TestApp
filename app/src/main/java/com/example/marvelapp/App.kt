package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.app.di.component.DaggerDataComponent
import com.example.marvelapp.app.di.component.DataComponent
import com.example.marvelapp.app.di.module.ContextModule

class App : Application() {

    private lateinit var dataComponent: DataComponent

    override fun onCreate() {
        super.onCreate()

        val contextModule = ContextModule(this)

        dataComponent = DaggerDataComponent.builder()
            .contextModule(contextModule)
            .build()

    }

    fun provideDataComponent(): DataComponent {
        return dataComponent
    }

}