package com.mhmdawad.torrentmovies.ui.fragments.details

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mhmdawad.torrentmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.screen_shot_movie_rv.view.*

class ScreenShotsAdapter(private val screenShotList: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return ScreenShotsViewHolder(
          LayoutInflater.from(parent.context).inflate(
                    R.layout.screen_shot_movie_rv,
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ScreenShotsViewHolder -> {
                holder.bind(screenShotList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return screenShotList.size
    }


    inner class ScreenShotsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) = with(itemView) {
            Picasso.get().load(item).into(screenShotImage)
        }
    }

}