package com.umutdemir.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.adapter.RecyclerPopularAdapter
import com.umutdemir.movieapp.viewmodel.PopularViewModel
import kotlinx.android.synthetic.main.fragment_popular.*


class PopularFragment : Fragment() {

    var popularPage = 1
    lateinit var viewModel: PopularViewModel
    var recylerPopularAdapter = RecyclerPopularAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberPicker.maxValue = 500
        numberPicker.minValue = 1

        viewModel = ViewModelProvider(this).get(PopularViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recylerPopularAdapter

        observeLiveData()
        viewModel.getPopular(popularPage)
        numberPicker.setOnValueChangedListener(OnValueChangeListener { numberPicker, oldVal, newVal ->
            popularPage = newVal
            viewModel.getPopular(popularPage)
            observeLiveData()

        })


    }

    fun observeLiveData() {
        viewModel.respondResult.observe(viewLifecycleOwner) {
            it?.let {

                recylerPopularAdapter.refreshList(it)

            }
        }
    }
}
