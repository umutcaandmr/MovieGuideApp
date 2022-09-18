package com.umutdemir.movieapp.model

data class ResponsePopular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)