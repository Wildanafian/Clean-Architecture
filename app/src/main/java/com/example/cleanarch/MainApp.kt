package com.example.cleanarch

import android.app.Application
import com.example.cleanarch.di.networkModule
import com.example.cleanarch.di.repoModule
import com.example.cleanarch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                networkModule,
                repoModule,
                viewModelModule
            )
        }
    }
}