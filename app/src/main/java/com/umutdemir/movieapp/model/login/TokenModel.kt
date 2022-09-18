package com.umutdemir.movieapp.model.login

data class TokenModel(

    val success: Boolean,
    val expires_at: String,
    val request_token: String

)