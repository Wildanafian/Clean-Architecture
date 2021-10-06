package com.example.cleanarch.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.cleanarch.data.network.model.ResponseData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiInterface {

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q: String?,
        @Query("page") page: Int?,
        @Query("apiKey") apiKey: String? = "5e51183454864effa9c541985ab6701c"
    ): Response<ResponseData>

    @GET("everything")
    suspend fun getAllNews2(
        @Query("q") q: String?,
        @Query("page") page: Int?,
        @Query("apiKey") apiKey: String? = "5e51183454864effa9c541985ab6701c"
    ): Response<ResponseData>

    @GET("everything")
    suspend fun getAllNews3(
        @Query("q") q: String?,
        @Query("page") page: Int?,
        @Query("apiKey") apiKey: String? = "5e51183454864effa9c541985ab6701c"
    ): Response<ResponseData>
}