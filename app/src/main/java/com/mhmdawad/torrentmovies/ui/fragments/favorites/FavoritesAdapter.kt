package com.mhmdawad.torrentmovies.ui.fragments.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.downloadImage
import kotlinx.android.synthetic.main.favorite_layout_rv.view.*

class FavoritesAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val favoritesList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_layout_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> {
                holder.bind(favoritesList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    fun addFavList(list: MutableList<String>) {
        favoritesList.apply {
            clear()
            addAll(list)
        }
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) = with(itemView) {

            imageView5.downloadImage(item)
            favMovieName.text = "Joker"
            favMovie.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                }

                override fun unLiked(likeButton: LikeButton?) {
                    removeAt(adapterPosition)
                    favMovie.isLiked = true
                }

            })
        }
    }

    private fun removeAt(position: Int) {
        favoritesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favoritesList.size)
    }

}