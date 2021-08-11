package com.example.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentNewsList : Fragment(R.layout.fragment_news_list){



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}