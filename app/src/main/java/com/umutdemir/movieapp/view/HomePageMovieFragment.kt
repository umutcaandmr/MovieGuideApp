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

class HomePageMovieFragment : Fragment() {

    lateinit var viewModel : HomePageViewModel
    var recylerPopularAdapter = RecyclerHomePageAdapter(arrayListOf())

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

        populer.setOnClickListener(){
            val action = HomePageMovieFragmentDirections.actionHomePageMovieFragmentToPopularFragment()
            Navigation.findNavController(it).navigate(action)
        }
        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = recylerPopularAdapter
        observeLiveData()
        viewModel.getPopular()
    }

    fun observeLiveData (){
        viewModel.respondPopular.observe(viewLifecycleOwner) {
            it?.let {

                recylerPopularAdapter.refreshList(it)

            }
        }
    }
}