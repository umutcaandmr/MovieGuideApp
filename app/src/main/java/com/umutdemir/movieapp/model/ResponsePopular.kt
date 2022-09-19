package com.umutdemir.movieapp.model

data class ResponsePopular(
    val page: Int,
    val results: List<Result>,
)