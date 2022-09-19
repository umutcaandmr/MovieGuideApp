package com.umutdemir.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umutdemir.movieapp.model.ResponseDetails
import com.umutdemir.movieapp.model.Result
import com.umutdemir.movieapp.servis.MovieApiServis
import kotlinx.coroutines.*

class DetailsViewModel : ViewModel() {

    val apiServis = MovieApiServis()
    val respondDetails = MutableLiveData<ResponseDetails>()
    var job : Job? = null

    fun getDetails(id : Int){

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiServis.getDetails(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    respondDetails.postValue(response.body())
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
