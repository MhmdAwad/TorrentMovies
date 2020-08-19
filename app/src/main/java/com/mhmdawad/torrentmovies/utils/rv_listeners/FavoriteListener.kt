package com.mhmdawad.torrentmovies.utils.rv_listeners

import android.widget.ImageView

interface FavoriteListener {

    fun onDeleteFavMovie(id: Int)

    fun onMovieClicked(id: Int, imageView: ImageView)
}