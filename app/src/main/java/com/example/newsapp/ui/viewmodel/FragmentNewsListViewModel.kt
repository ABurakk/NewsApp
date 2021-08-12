package com.example.newsapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.NewsRepository
import com.example.newsapp.data.Article
import com.example.newsapp.other.Resource
import com.example.newsapp.api.NewsService
import kotlinx.coroutines.launch

class FragmentNewsListViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {


    private var _newsListLiveData = MutableLiveData<Resource<List<Article>>>()

    var newsListLiveData : LiveData<Resource<List<Article>>> = _newsListLiveData


    fun getNews(){
        viewModelScope.launch {
            _newsListLiveData.postValue(repository.getNewsByDefault())
        }
    }

}