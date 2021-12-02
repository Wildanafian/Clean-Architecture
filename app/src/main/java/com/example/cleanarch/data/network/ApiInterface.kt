package com.example.cleanarch.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.cleanarch.data.model.ResponseData

interface ApiInterface {

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q: String?,
        @Query("page") page: Int?,
        @Query("apiKey") apiKey: String? = "a5bf56b6153c4ee9bd64368cba3e1317"
    ): Response<ResponseData>

}