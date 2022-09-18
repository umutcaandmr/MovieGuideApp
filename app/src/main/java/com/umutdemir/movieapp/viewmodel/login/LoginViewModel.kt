package com.umutdemir.movieapp.viewmodel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umutdemir.movieapp.model.login.LoginModel
import com.umutdemir.movieapp.model.login.TokenModel
import com.umutdemir.movieapp.servis.login.LoginApiServis
import kotlinx.coroutines.*

class LoginViewModel  : ViewModel()
{
    val apiServis = LoginApiServis()
    val errorLogin = MutableLiveData<Boolean>()
    val errorToken = MutableLiveData<Boolean>()
    val respondLogin = MutableLiveData<TokenModel>()
    val respondToken = MutableLiveData<TokenModel>()
    var jobLogin: Job? = null
    var jobToken : Job? = null


    fun sendLogin(loginModel: LoginModel){

        jobLogin = CoroutineScope(Dispatchers.Main).launch {
            val response = apiServis.sendLogin(loginModel)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    respondLogin.postValue(response.body())
                }

                else{
                    errorLogin.postValue(true)
                }
            }
        }
    }
    fun getToken(){
        jobToken = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getToken()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    respondToken.postValue(response.body())
                }

                else{
                    errorToken.value = true
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobLogin?.cancel()
        jobToken?.cancel()
    }




}