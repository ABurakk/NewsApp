package com.example.newsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.other.downloadImage
import com.example.newsapp.ui.fragment.FragmentNewsList
import com.example.newsapp.ui.fragment.FragmentNewsListDirections
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.acos

class NewsListAdapter(var view : View , var newsList : List<Article>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {




    class ViewHolder (binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){


        var imgNews = binding.ivNews
        var tvTitle = binding.tvTitle
        var tvDate = binding.tvDate
        var description = binding.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        newsList.get(position).let {
            holder.tvTitle.text = it.title
            holder.description.text = it.description
            holder.tvDate.text = it.publishedAt
            holder.imgNews.downloadImage(it.urlToImage)
            val article = it
            holder.itemView.setOnClickListener {
                val action =FragmentNewsListDirections.actionFragmentNewsListToFragmentNewsDetail(article.title,article.description,article.url,article.urlToImage)
                Navigation.findNavController(view).navigate(action)
            }


        }


    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}