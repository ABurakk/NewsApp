package com.example.newsapp.repository

import com.example.newsapp.data.Article
import com.example.newsapp.other.Resource

class DefaultNewsRepository : NewsRepository{


    override suspend fun getNewsByDefault(): Resource<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsByDate(): Resource<List<Article>> {
        TODO("Not yet implemented")
    }
}