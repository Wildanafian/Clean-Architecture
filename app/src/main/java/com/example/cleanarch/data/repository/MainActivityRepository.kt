package com.example.cleanarch.data.repository

import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.data.network.model.NewsData
import com.example.cleanarch.data.network.model.ResponseData
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {
    fun getNewsFromApi(): Flow<Resource<List<NewsData>>>
    suspend fun getNewsFromApi2()
}