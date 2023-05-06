package com.route.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageFromUrl(imgeView:ImageView,url:String){
    Glide.with(imgeView).load(url).into(imgeView)
}