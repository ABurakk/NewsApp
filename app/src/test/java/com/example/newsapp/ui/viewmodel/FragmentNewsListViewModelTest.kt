package com.example.newsapp.ui.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.Repositories.FakeNewsRepository
import com.example.newsapp.other.Resource
import com.example.newsapp.ui.getOrAwaitValueTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.Rule
import javax.net.ssl.SSLEngineResult

@ExperimentalCoroutinesApi
class FragmentNewsListViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()




    lateinit var viewmodel : FragmentNewsListViewModel
    lateinit var repository : FakeNewsRepository

    @Before
    fun setUp(){

     repository = FakeNewsRepository()

     viewmodel = FragmentNewsListViewModel(repository)
    }

    @Test
    fun getNews(){

        var result = true

        viewmodel.getNews("football","1234","2021-08-05","2021-08-10")

        val x=viewmodel.newsListLiveData.getOrAwaitValueTest()

        when(x){

            is Resource.Success -> result = true

            is Resource.Error -> result = false
        }

        assertThat(result).isTrue()
    }

}