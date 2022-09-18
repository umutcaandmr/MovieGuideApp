package com.umutdemir.movieapp.servis

import com.umutdemir.movieapp.model.ResponsePopular
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    suspend fun getPopular() : Response<ResponsePopular>


}