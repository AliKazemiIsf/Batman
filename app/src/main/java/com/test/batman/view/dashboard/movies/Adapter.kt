package com.test.batman.view.dashboard.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.batman.R
import com.test.batman.data.local.entity.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class Adapter(
    private val list: List<Movie>,
    private val callback: (String, String, String, String)-> Unit
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(model: Movie){
            itemView.txtTitle.text = model.xTitle
            itemView.txtYear.text = model.xYear
            Glide.with(itemView.context)
                .load(model.xPosterUrl)
                .into(itemView.thumbnail)
            itemView.setOnClickListener {
                callback.invoke(model.xImdbId, model.xTitle ?: "", model.xPosterUrl?: "", model.xYear ?: "")
            }
        }
    }
}