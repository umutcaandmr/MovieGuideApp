package com.umutdemir.movieapp.servis

import com.umutdemir.movieapp.model.ResponseDetails
import com.umutdemir.movieapp.model.ResponsePopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    suspend fun getPopular(
        @Query("page") page : Int) : Response<ResponsePopular>

    @GET("movie/{movie_id}?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    suspend fun getDetails(@Path("movie_id") id : Int) : Response<ResponseDetails>


    @GET("movie/top_rated?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    suspend fun getTopRated(
        @Query("page") page : Int) : Response<ResponsePopular>

    @GET("movie/now_playing?api_key=5976a1fbe7f09d49c7d313c485adfcac&language=tr-TR")
    suspend fun getNowPlaying(
        @Query("page") page : Int) : Response<ResponsePopular>
}