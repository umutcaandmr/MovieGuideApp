package com.umutdemir.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.adapter.RecyclerHomePageAdapter
import com.umutdemir.movieapp.adapter.RecyclerPopularAdapter
import com.umutdemir.movieapp.viewmodel.HomePageViewModel
import com.umutdemir.movieapp.viewmodel.PopularViewModel
import kotlinx.android.synthetic.main.fragment_home_page_movie.*
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_popular.recyclerView
import kotlin.random.Random

class HomePageMovieFragment : Fragment() {

    lateinit var viewModel : HomePageViewModel
    var recylerPopularAdapter = RecyclerHomePageAdapter(arrayListOf())
    var recylerTopRatedAdapter = RecyclerHomePageAdapter(arrayListOf())
    var recyclerNowPlayingAdapter = RecyclerHomePageAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       var page  =  Random.nextInt(1,5)

        populer.setOnClickListener(){
            val action = HomePageMovieFragmentDirections.actionHomePageMovieFragmentToPopularFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)

        recyclerViewPopular.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewPopular.adapter = recylerPopularAdapter
        recyclerViewVizyon.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewVizyon.adapter = recyclerNowPlayingAdapter
        recyclerViewTopRated.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewTopRated.adapter = recylerTopRatedAdapter

        viewModel.getNowPlaying(page)
        viewModel.getTopRated(page)
        viewModel.getPopular(page)
        observeLiveData()

    }

    fun observeLiveData (){
        viewModel.respondPopular.observe(viewLifecycleOwner) {
            it?.let {

                recylerPopularAdapter.refreshList(it)

            }
        }

        viewModel.respondTopRated.observe(viewLifecycleOwner){
            it?.let {
                recylerTopRatedAdapter.refreshList(it)
            }
        }

        viewModel.respondNowPlaying.observe(viewLifecycleOwner) {
            it?.let {

                recyclerNowPlayingAdapter.refreshList(it)

            }
        }
    }
}