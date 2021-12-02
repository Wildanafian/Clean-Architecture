package com.example.cleanarch.di

import com.example.cleanarch.data.repository.MainActivityRepository
import com.example.cleanarch.domain.MainActivityUseCase
import com.example.cleanarch.domain.MainActivityUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(repository: MainActivityRepository): MainActivityUseCase =
        MainActivityUseCaseImpl(repository)

}