package com.example.newsapp.api

import com.example.newsapp.data.News
import com.example.newsapp.other.Resource

interface NewsRepository {

    suspend fun getNewsByApi(q:String, apiKey:String, dateFrom:String, dateTo:String):Resource<News>
}