package com.example.cleanarch.repository

import com.example.cleanarch.data.local.RoomInterface
import com.example.cleanarch.data.remote.ApiInterface
import com.example.cleanarch.data.remote.BaseDataSource
import com.example.cleanarch.data.remote.Resource
import com.example.cleanarch.model.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(private val api: ApiInterface, private val room: RoomInterface) : MainActivityRepository, BaseDataSource() {

    private val data = ArrayList<NewsData>()

    override fun getData(): Flow<Resource<ArrayList<NewsData>?>> {
        return flow {
            val result = getResult { api.getAllNews("tesla", 1) }
            if (result.status == Resource.Status.SUCCESS) {
                result.data?.articles?.map {
                    data.add(NewsData(it.publishedAt, it.author, it.urlToImage, it.description, it.title))
                }
                emit(Resource.success(data))
                room.delete()
                room.insertAllNews(data)
            } else if (result.status == Resource.Status.ERROR) {
                emit(Resource.error(result.message ?: "Error", room.getAllNews() as ArrayList))
            }
        }.onStart {
            emit(Resource.loading(room.getAllNews() as ArrayList))
        }.flowOn(Dispatchers.IO)
    }
}
