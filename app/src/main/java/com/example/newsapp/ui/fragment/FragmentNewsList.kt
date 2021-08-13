package com.example.newsapp.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.other.Resource
import com.example.newsapp.ui.viewmodel.FragmentNewsListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class FragmentNewsList : Fragment(R.layout.fragment_news_list){


    lateinit var viewmodel : FragmentNewsListViewModel
    private lateinit var binding : FragmentNewsListBinding
    lateinit var firstDay : LocalDateTime
    lateinit var format : DateTimeFormatter
    lateinit var current :LocalDateTime
    lateinit var recyclerViewAdapter : NewsListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var todayDateText : String
    lateinit var firstDayDateText : String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsListBinding.bind(view)
        binding.progressBar.visibility = View.INVISIBLE
        viewmodel = ViewModelProvider(requireActivity()).get(FragmentNewsListViewModel::class.java)

        setDateVariable()
        setRecyclerViewAdapter()
        viewmodel.getNews("football","ae68088e70d04639b4950bdc9d546924",firstDayDateText,todayDateText)







//
        watchToNewsListObserver()
    }


    fun setDateVariable(){
        format = DateTimeFormatter.ISO_DATE
        current = LocalDateTime.now()
        firstDay = current.minusDays(10)
        todayDateText = current.format(format)
        firstDayDateText = firstDay.format(format)
    }


    fun setRecyclerViewAdapter(){
        recyclerViewAdapter = NewsListAdapter(listOf())
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewNews.layoutManager = linearLayoutManager
        binding.recyclerViewNews.adapter = recyclerViewAdapter
    }

    fun watchToNewsListObserver(){
       viewmodel.newsListLiveData.observe(requireActivity()){
           when(it){
               is Resource.Error -> {
                   Snackbar.make(requireView(),"${it.message}",Snackbar.LENGTH_SHORT).show()
                   binding.progressBar.visibility = View.INVISIBLE
               }

               is Resource.Loading -> {
                   binding.progressBar.visibility = View.VISIBLE

               }

               is Resource.Success -> {
                   binding.progressBar.visibility = View.INVISIBLE
                   it.data?.let {
                       recyclerViewAdapter.newsList = it.articles
                       recyclerViewAdapter.notifyDataSetChanged()
                   }


               }

           }
       }

    }


}