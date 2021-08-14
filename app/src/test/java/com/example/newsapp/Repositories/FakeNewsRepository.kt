package com.example.newsapp.Repositories

import com.example.newsapp.api.NewsRepository
import com.example.newsapp.data.Article
import com.example.newsapp.data.News
import com.example.newsapp.data.Source
import com.example.newsapp.other.Resource

class FakeNewsRepository : NewsRepository {


    val article = Article("ahmet","futbol","futbol1","2021-08-11", Source("",""),"Futbol","","")
    val News = News(listOf(article),"",1)

    override suspend fun getNewsByApi(q: String,apiKey: String, dateFrom: String, dateTo: String
    ): Resource<News> {
        if(q!=null&&apiKey!=null&&dateFrom!=null&&dateTo!=null)
            return Resource.Success(News)

       return Resource.Error("An error occured")
    }
}