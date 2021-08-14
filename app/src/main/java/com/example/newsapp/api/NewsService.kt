package com.example.newsapp.api

import com.example.newsapp.data.Article
import com.example.newsapp.data.News
import com.example.newsapp.other.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {



    @GET("everything/")
    suspend fun getNewsByDefault(@Query("q") q:String, @Query("apiKey") apiKey:String,@Query("from") date:String,@Query("to") date2:String) : Response<News>

}