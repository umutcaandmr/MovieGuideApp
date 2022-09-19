package com.umutdemir.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umutdemir.movieapp.model.Result
import com.umutdemir.movieapp.servis.MovieApiServis
import kotlinx.coroutines.*

class HomePageViewModel : ViewModel() {

    val apiServis = MovieApiServis()
    val respondPopular = MutableLiveData<List<Result>>()
    val respondTopRated = MutableLiveData<List<Result>>()
    val respondNowPlaying = MutableLiveData<List<Result>>()
    var jobPopular : Job? = null
    var jobTopRated : Job? = null
    var jobNowPlaying : Job? = null

    fun getPopular(page : Int){

        jobPopular = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getPopular(page)
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

    fun getTopRated(page : Int){

        jobPopular = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getTopRated(page)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    respondTopRated.postValue(response.body()!!.results)
                }

                else{
                    println(response.errorBody()!!.toString())
                }

            }
        }

    }

    fun getNowPlaying(page : Int){

        jobPopular = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getNowPlaying(page)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    respondNowPlaying.postValue(response.body()!!.results)
                }

                else{
                    println(response.errorBody()!!.toString())
                }

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        jobPopular?.cancel()
        jobNowPlaying?.cancel()
        jobTopRated?.cancel()
    }
}