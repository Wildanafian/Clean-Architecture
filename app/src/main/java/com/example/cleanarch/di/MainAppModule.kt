package com.example.cleanarch.di

import android.content.Context
import com.example.cleanarch.MainApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainAppModule {
    @Singleton
    @Provides
    fun appContext(@ApplicationContext context: Context) : MainApp{
        return context as MainApp
    }
}