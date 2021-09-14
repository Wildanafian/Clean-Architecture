package com.example.cleanarch.repository

import com.example.cleanarch.model.NewsData
import com.example.cleanarch.model.ResponseData
import com.example.cleanarch.network.ApiInterface
import com.example.cleanarch.network.BaseDataSource
import com.example.cleanarch.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(private val api: ApiInterface) : MainActivityRepository, BaseDataSource() {

    private val data = ArrayList<NewsData>()

    override fun getData(): Flow<Resource<ArrayList<NewsData>?>> {
        return flow {
            val result = getResult { api.getAllNews("tesla",1) }
            if (result.status == Resource.Status.SUCCESS) {
                result.data?.articles?.map {
                    data.add(NewsData(it.publishedAt, it.author, it.urlToImage, it.description, it.title))
                }
                emit(Resource.success(data))
            }
            else if (result.status == Resource.Status.ERROR) {
                emit(Resource.error(result.message!!))
            }
        }.onStart {
            emit(Resource.loading())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun asd(): Resource<ResponseData?> {
        return withContext(Dispatchers.IO) {
            val response: Resource<ResponseData> = getResult { api.getAllNews("tesla", 1) }
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    Resource.success(response.data)
                }
                Resource.Status.ERROR -> {
                    Resource.error(response.message!!,null)
                }
                else -> {
                    Resource.error(response.message!!,null)
                }
            }
        }
    }

}
