package com.example.cleanarch.data.repository

import com.example.cleanarch.data.model.ArticlesItem
import com.example.cleanarch.data.model.NewsData
import com.example.cleanarch.data.network.BaseDataSource
import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.data.repository.remotedatasource.NewsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.net.SocketException
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(private val remoteData: NewsRemoteDataSource) :
    MainActivityRepository, BaseDataSource() {

    override fun getNewsFromApi(): Flow<Resource<List<NewsData>>> = flow {
        val (status, data, message) = remoteData.getNews()
        if (status == Resource.Status.SUCCESS) {
            emit(Resource.success(data?.articles?.mapToNewsData()!!))
        } else {
            emit(Resource.error(message ?: "Eror", null))
        }
    }.onStart {
        Resource.loading(null)
    }.onCompletion {
        Resource.complete(null)
    }.retryWhen { cause, attempt ->
        cause is SocketException
    }.catch {
        emit(Resource.error(it.localizedMessage ?: "Eror", null))
    }.flowOn(Dispatchers.IO)

    private fun List<ArticlesItem>.mapToNewsData(): List<NewsData> {
        return this.map {
            NewsData(it.publishedAt, it.author, it.urlToImage, it.description, it.title)
        }
    }
}