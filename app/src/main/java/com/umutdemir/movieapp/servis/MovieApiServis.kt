package com.umutdemir.movieapp.servis

import com.umutdemir.movieapp.model.ResponsePopular
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiServis {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    suspend fun getPopular() : Response<ResponsePopular>{
        return api.getPopular()
    }
}