package com.umutdemir.movieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.util.downloadPoster
import com.umutdemir.movieapp.viewmodel.DetailsViewModel
import com.umutdemir.movieapp.viewmodel.PopularViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.movieName
import kotlinx.android.synthetic.main.fragment_details.movieVote
import kotlinx.android.synthetic.main.recycler_row_popular.*

class DetailsFragment : Fragment() {

    var movieID = 0
    lateinit var viewModel : DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
         movieID =  DetailsFragmentArgs.fromBundle(it).movieID
        }

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        viewModel.getDetails(movieID)

        viewModel.respondDetails.observe(viewLifecycleOwner){
            movieName.text = it.title
            movieOriginialName.text = it.original_title
            movieBudget.text = "Bütçe : ${it.budget}$"
            movieImage.downloadPoster(it.backdrop_path)
            movieDate.text = "Yayınlanma Tarihi : ${it.release_date}"
            movieOverview.text = it.overview
            movieVote.text = "Değerlendirme : ${it.vote_average}/10  (${it.vote_count})"
            movieTime.text = "Süre : ${it.runtime} dakika"
            genelBakis.text = "Genel Bakış"
        }






    }
}