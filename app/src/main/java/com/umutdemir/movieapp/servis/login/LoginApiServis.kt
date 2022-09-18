package com.umutdemir.movieapp.servis.login

import com.umutdemir.movieapp.model.login.LoginModel
import com.umutdemir.movieapp.model.login.TokenModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginApiServis {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginApi::class.java)


    suspend fun getToken() : Response<TokenModel> {
        return api.getToken()
    }

    suspend fun sendLogin(loginModel: LoginModel) : Response<TokenModel> {
        return api.sendLogin(loginModel)
    }


}