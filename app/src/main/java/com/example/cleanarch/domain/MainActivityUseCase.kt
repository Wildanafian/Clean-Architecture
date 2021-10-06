package com.example.cleanarch.domain

import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.data.network.model.NewsData
import kotlinx.coroutines.flow.Flow

interface MainActivityUseCase {
    fun getNews(): Flow<Resource<List<NewsData>>>
    suspend fun getNews2()
}