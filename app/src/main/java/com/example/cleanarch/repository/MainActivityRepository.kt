package com.example.cleanarch.repository

import com.example.cleanarch.model.NewsData
import com.example.cleanarch.network.Resource
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {
    fun getData(): Flow<Resource<ArrayList<NewsData>?>>
}