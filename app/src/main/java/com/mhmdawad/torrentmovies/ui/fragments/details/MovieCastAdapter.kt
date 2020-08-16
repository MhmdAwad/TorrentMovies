package com.mhmdawad.torrentmovies.ui.fragments.details

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.CastItem
import com.mhmdawad.torrentmovies.utils.downloadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_cast_rv.view.*

class MovieCastAdapter(private val castList: List<CastItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_cast_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> {
                holder.bind(castList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return castList.size
    }


    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CastItem) = with(itemView) {
            castImage.downloadImage(item.urlSmallImage, true)
            castName.text = item.name
        }
    }

}