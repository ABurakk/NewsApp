package com.example.newsapp.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.other.Resource
import com.example.newsapp.other.constant
import com.example.newsapp.ui.viewmodel.FragmentNewsListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class FragmentNewsList : Fragment(R.layout.fragment_news_list){


    lateinit var viewmodel : FragmentNewsListViewModel
    private lateinit var binding : FragmentNewsListBinding
    lateinit var startDayDay : LocalDateTime
    lateinit var format : DateTimeFormatter
    lateinit var current :LocalDateTime
    lateinit var recyclerViewAdapter : NewsListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var currentDateText : String
    lateinit var firstDayDateText : String
    lateinit var fav:MenuItem


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsListBinding.bind(view)
        binding.progressBar.visibility = View.INVISIBLE
        viewmodel = ViewModelProvider(requireActivity()).get(FragmentNewsListViewModel::class.java)

        initilazeDateVariable()
        initilazeRecyclerViewAdapter()
        viewmodel.getNews(constant.queryParemeter,constant.apiKey,firstDayDateText,currentDateText)


        setHasOptionsMenu(true)

        watchToNewsListObserver()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
           R.id.filterDate -> {
               val datepicker = DatePickerDialog(requireContext())

               datepicker.setOnDateSetListener( { datePicker, year, month, day ->
                   firstDayDateText=year.toString()+"-"+(month+1).toString()+"-"+day.toString()
                   viewmodel.getNews(constant.queryParemeter,constant.apiKey,firstDayDateText,currentDateText)
               })
                   datepicker.show()

           }
        }

        return true
    }


    fun initilazeDateVariable(){
        format = DateTimeFormatter.ISO_DATE
        current = LocalDateTime.now()
        startDayDay = current.minusDays(10)
        currentDateText = current.format(format)
        firstDayDateText = startDayDay.format(format)
    }
    fun initilazeRecyclerViewAdapter(){
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