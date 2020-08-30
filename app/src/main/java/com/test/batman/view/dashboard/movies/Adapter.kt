package com.test.batman.view.dashboard.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.batman.R
import com.test.batman.data.network.model.BatmanMoviesResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class Adapter(
    private val list: List<BatmanMoviesResponse.Result>,
    private val callback: (String)-> Unit
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(model: BatmanMoviesResponse.Result){
            itemView.txtTitle.text = model.title
            itemView.txtYear.text = model.year
            Glide.with(itemView.context)
                .load(model.posterUrl)
                .into(itemView.thumbnail)
            itemView.setOnClickListener {
                callback.invoke(model.imdbId ?: "")
            }
        }
    }
}