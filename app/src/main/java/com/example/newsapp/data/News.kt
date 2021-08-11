package com.example.newsapp.data


//Created With Json to Object plugin
data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)