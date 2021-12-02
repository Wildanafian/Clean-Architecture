package com.example.cleanarch.data.repository

import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.data.model.NewsData
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {
    fun getNewsFromApi(): Flow<Resource<List<NewsData>>>
}