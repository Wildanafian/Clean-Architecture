package com.example.cleanarch.di

import com.example.cleanarch.NewsAdapter
import com.example.cleanarch.network.ApiInterface
import com.example.cleanarch.repository.MainActivityRepository
import com.example.cleanarch.repository.MainActivityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun mainRepository(api: ApiInterface): MainActivityRepository{
        return MainActivityRepositoryImpl(api)
    }

//    @Singleton
//    @Provides
//    fun mainRepository(api: ApiInterface) = MainActivityRepositoryImpl(api)

//    @Singleton
//    @Provides
//    fun newsAdapter() = NewsAdapter()
}