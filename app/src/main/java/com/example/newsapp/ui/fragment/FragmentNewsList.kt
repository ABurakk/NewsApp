package com.example.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.other.Resource
import com.example.newsapp.ui.viewmodel.FragmentNewsListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentNewsList : Fragment(R.layout.fragment_news_list){


    lateinit var viewmodel : FragmentNewsListViewModel
    private lateinit var binding : FragmentNewsListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsListBinding.bind(view)
        binding.progressBar.visibility = View.INVISIBLE
        viewmodel = ViewModelProvider(requireActivity()).get(FragmentNewsListViewModel::class.java)

        viewmodel.getNews("football","ae68088e70d04639b4950bdc9d546924")
        Log.d("newsList","hello from news list fragment")



        watchToNewsListObserver()
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
                       Log.d("newsList",it.totalResults.toString())
                   }
                   Log.d("newsList","succes")


               }

           }
       }

    }


}