package com.example.newsapp.api

import android.util.Log
import com.example.newsapp.data.News
import com.example.newsapp.other.Resource
import retrofit2.Response

class NewsRepository(private val service: NewsService) {

    suspend fun getNewsByDefault(q:String,apiKey:String,dateFrom:String,dateTo:String) : Resource<News>{

        val response = service.getNewsByDefault(q, apiKey,dateFrom,dateTo)

        if(response.isSuccessful){
            val responseBody = response.body()
            return Resource.Success(responseBody)
        }

        return Resource.Error(response.errorBody().toString())
    }

}