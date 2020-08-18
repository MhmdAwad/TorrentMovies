package com.mhmdawad.torrentmovies.ui.fragments.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.utils.downloadImage
import com.mhmdawad.torrentmovies.utils.rv_listeners.FavoriteListener
import kotlinx.android.synthetic.main.favorite_layout_rv.view.*

class FavoritesAdapter(private val favoriteListener: FavoriteListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val favoritesList: MutableList<FavoriteMovie> = mutableListOf()
    private var lastPosition = 0

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

    fun addFavList(list: List<FavoriteMovie>) {
        favoritesList.apply {
            clear()
            addAll(list)
            notifyItemRangeChanged(lastPosition, favoritesList.size)
        }
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: FavoriteMovie) = with(itemView) {
            favMovie.apply {
                setOnLikeListener(object : OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        lastPosition = adapterPosition
                        favoriteListener.onDeleteFavMovie(item.id!!)
                    }
                })
                isLiked = true
            }
            imageView5.downloadImage(item.mediumCoverImage)
            favMovieName.text = item.titleEnglish
        }
    }


}