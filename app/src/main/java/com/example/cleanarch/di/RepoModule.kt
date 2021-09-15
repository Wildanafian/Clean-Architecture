package com.example.cleanarch.di

import com.example.cleanarch.feature.news.NewsFragmentRepository
import com.example.cleanarch.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun mainActivityRepository(api: ApiInterface)= NewsFragmentRepository(api)
}