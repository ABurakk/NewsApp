package com.example.newsapp.other

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadImage(url:String){

    Glide.with(context)
        .load(url)
        .into(this)
}