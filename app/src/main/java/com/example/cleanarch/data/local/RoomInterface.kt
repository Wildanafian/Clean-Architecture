package com.example.cleanarch.data.local

import androidx.room.*
import com.example.cleanarch.model.NewsData

@Dao
interface RoomInterface {
    @Query("SELECT * FROM newsData")
    suspend fun getAllNews(): List<NewsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(newsDataLocal: List<NewsData>)

    @Query("DELETE from newsData")
    suspend fun delete()
}