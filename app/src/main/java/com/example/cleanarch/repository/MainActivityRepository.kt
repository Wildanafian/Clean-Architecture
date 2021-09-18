package com.example.cleanarch.repository

import com.example.cleanarch.model.NewsData
import com.example.cleanarch.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {
    fun getData(): Flow<Resource<ArrayList<NewsData>?>>
}