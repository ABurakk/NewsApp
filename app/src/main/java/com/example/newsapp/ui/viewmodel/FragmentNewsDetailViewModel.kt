package com.example.newsapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.DeafultNewsRepository
import com.example.newsapp.api.NewsRepository

class FragmentNewsDetailViewModel @ViewModelInject constructor(
    val repository: NewsRepository
) : ViewModel() {



}