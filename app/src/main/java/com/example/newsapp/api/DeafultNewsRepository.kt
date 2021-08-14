package com.example.newsapp.api

import android.util.Log
import com.example.newsapp.data.News
import com.example.newsapp.other.Resource
import retrofit2.Response

class DeafultNewsRepository(private val service: NewsService) : NewsRepository {

    override suspend fun getNewsByApi(q:String, apiKey:String, dateFrom:String, dateTo:String) : Resource<News>{

        try{
            val response = service.getNewsByDefault(q, apiKey,dateFrom,dateTo)

            if(response.isSuccessful){
                val responseBody = response.body()
                return Resource.Success(responseBody)
            }

            return Resource.Error("Error"+response.code().toString())
        }catch (e:Exception){
            return Resource.Error("Couldn't reach the server")
        }
    }



}