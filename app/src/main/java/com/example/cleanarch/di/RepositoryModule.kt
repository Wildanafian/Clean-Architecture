package com.example.cleanarch.di

import com.example.cleanarch.MainActivityRepository
import org.koin.dsl.module

val repoModule = module {
    single { MainActivityRepository(get()) }
}