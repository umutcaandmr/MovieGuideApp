package com.umutdemir.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.model.Result
import com.umutdemir.movieapp.util.downloadPoster
import kotlinx.android.synthetic.main.recycler_row_popular.view.*

class RecyclerPopularAdapter(val responsePopular :ArrayList<Result>) : RecyclerView.Adapter<RecyclerPopularAdapter.VH>() {
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.recycler_row_popular,parent,false)
        return VH(view)

    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.itemView.movieName.text = responsePopular.get(position).title
        holder.itemView.movieYear.text = responsePopular.get(position).release_date.subSequence(0,4)
        holder.itemView.movieVote.text = responsePopular.get(position).vote_average.toString()
        holder.itemView.moviePopularity.text = responsePopular.get(position).overview
        holder.itemView.imageView.downloadPoster(responsePopular.get(position).poster_path)
    }

    override fun getItemCount(): Int {
        return responsePopular.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(yeniBesinListesi : List<Result>){
        responsePopular.clear()
        responsePopular.addAll(yeniBesinListesi)
        notifyDataSetChanged()

    }

}