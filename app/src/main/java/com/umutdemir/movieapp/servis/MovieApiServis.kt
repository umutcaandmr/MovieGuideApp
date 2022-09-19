package com.umutdemir.movieapp.servis

import com.umutdemir.movieapp.model.ResponseDetails
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

    suspend fun getPopular(page : Int) : Response<ResponsePopular>{
        return api.getPopular(page)
    }

    suspend fun getTopRated(page : Int) : Response<ResponsePopular>{
        return api.getTopRated(page)
    }

    suspend fun getDetails(id : Int) : Response<ResponseDetails>{
        return api.getDetails(id)
    }

    suspend fun getNowPlaying(page : Int) : Response<ResponsePopular>{
        return api.getNowPlaying(page)
    }
}