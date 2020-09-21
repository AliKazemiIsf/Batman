package com.test.batman.view.dashboard.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.batman.R
import com.test.batman.data.local.entity.Rating
import com.test.batman.data.network.model.MovieDetailResponse
import kotlinx.android.synthetic.main.item_rating.view.*

class Adapter(
    private val list: List<Rating>
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rating, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(model: Rating){
            itemView.txtRatingSource.text = model.xSource
            itemView.txtRatingValue.text = model.xValue
        }
    }
}