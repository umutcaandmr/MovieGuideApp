package com.umutdemir.movieapp.model

data class Result(

    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)