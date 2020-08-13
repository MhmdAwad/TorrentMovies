package com.mhmdawad.torrentmovies.utils

import android.widget.ImageView

interface AdapterListener {

    fun itemClicked(pos: Int)

    fun openMovie(movieID: Int,movieBackground: String, imageView: ImageView)

}