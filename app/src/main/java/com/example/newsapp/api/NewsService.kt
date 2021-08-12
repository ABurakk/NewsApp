package com.example.newsapp.api

import com.example.newsapp.data.Article
import com.example.newsapp.other.Resource

interface NewsService {


    suspend fun getNewsByDefault() : Resource<List<Article>>


    suspend fun getNewsByDate() : Resource<List<Article>>

}