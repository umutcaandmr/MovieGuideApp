package com.umutdemir.movieapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umutdemir.movieapp.R


/*
fun String.benimEklentim(parametre : String?){
    println(parametre)
}
 */


fun ImageView.downloadPoster(url : String?){
    val BASE_URL = "https://image.tmdb.org/t/p/w500/"
    val options = RequestOptions().placeholder(null).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(BASE_URL + url).into(this)
}



