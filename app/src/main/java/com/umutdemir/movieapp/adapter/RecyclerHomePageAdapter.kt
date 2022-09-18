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

class RecyclerHomePageAdapter(val responseMovie :ArrayList<Result>) : RecyclerView.Adapter<RecyclerHomePageAdapter.HomeVH>() {

    class HomeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.recycler_row_homepage,parent,false)
        return HomeVH(view)

    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.itemView.imageView.downloadPoster(responseMovie.get(position).poster_path)
        holder.itemView.movieName.text = responseMovie.get(position).title
    }

    override fun getItemCount(): Int {
        return responseMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(yeniBesinListesi : List<Result>){
        responseMovie.clear()
        responseMovie.addAll(yeniBesinListesi)
        notifyDataSetChanged()

    }
}