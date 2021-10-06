package com.example.cleanarch.domain

import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.data.network.model.NewsData
import com.example.cleanarch.data.repository.MainActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainActivityUseCaseImpl @Inject constructor(private val repository: MainActivityRepository): MainActivityUseCase {

    override fun getNews(): Flow<Resource<List<NewsData>>> = repository.getNewsFromApi()
    override suspend fun getNews2() = repository.getNewsFromApi2()
}