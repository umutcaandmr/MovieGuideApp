package com.umutdemir.movieapp.servis.login

import com.umutdemir.movieapp.model.login.LoginModel
import com.umutdemir.movieapp.model.login.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @GET("authentication/token/new?api_key=5976a1fbe7f09d49c7d313c485adfcac")
    suspend fun getToken() : Response<TokenModel>


    @POST("authentication/token/validate_with_login?api_key=5976a1fbe7f09d49c7d313c485adfcac")
    suspend fun sendLogin(@Body login : LoginModel) : Response<TokenModel>

}