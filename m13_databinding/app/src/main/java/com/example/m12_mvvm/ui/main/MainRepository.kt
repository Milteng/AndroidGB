package com.example.m12_mvvm.ui.main

import kotlinx.coroutines.delay

class MainRepository {
    suspend fun getData(query: String): String {
        delay(5_000)
        return "Done"
    }
}