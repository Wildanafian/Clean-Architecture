package com.example.cleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarch.model.NewsData

@Database(entities = [NewsData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): RoomInterface
}