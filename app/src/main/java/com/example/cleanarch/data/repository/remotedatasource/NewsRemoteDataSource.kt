package com.example.cleanarch.data.repository.remotedatasource

import com.example.cleanarch.data.network.ApiInterface
import com.example.cleanarch.data.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val api: ApiInterface) : BaseDataSource() {

    suspend fun getNews() = withContext(Dispatchers.IO){
        getResult { api.getAllNews("tesla", 1) }
    }
}