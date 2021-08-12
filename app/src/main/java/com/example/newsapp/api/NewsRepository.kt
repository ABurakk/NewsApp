package com.example.newsapp.api

class NewsRepository(private val service: NewsService) {

    suspend fun getNewsByDefault() = service.getNewsByDefault()

}