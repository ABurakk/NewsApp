package com.example.newsapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.other.downloadImage
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FragmentNewsDetail : Fragment(R.layout.fragment_news_detail) {


    val args : FragmentNewsDetailArgs by navArgs()
    private lateinit var binding : FragmentNewsDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailBinding.bind(view)

        binding.tvDetailTitle.text = args.title
        binding.tvDetailDecription.text = args.description
        binding.ivDetailNewsImage.downloadImage(args.urlToImage)

        binding.button.setOnClickListener {
            val url = args.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


    }
}