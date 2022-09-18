package com.umutdemir.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umutdemir.movieapp.model.Result
import com.umutdemir.movieapp.servis.MovieApiServis
import kotlinx.coroutines.*

class HomePageViewModel : ViewModel() {

    val apiServis = MovieApiServis()
    val respondPopular = MutableLiveData<List<Result>>()
    var job : Job? = null

    fun getPopular(){

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getPopular()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    respondPopular.postValue(response.body()!!.results)
                }

                else{
                    println(response.errorBody()!!.toString())
                }

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}