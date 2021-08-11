package com.example.newsapp.repository

import com.example.newsapp.data.Article
import com.example.newsapp.data.News
import com.example.newsapp.other.Resource

interface NewsRepository {


    suspend fun getNewsByDefault() : Resource<List<Article>>


    suspend fun getNewsByDate() : Resource<List<Article>>

}