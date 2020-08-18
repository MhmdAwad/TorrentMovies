package com.mhmdawad.torrentmovies.utils.rv_listeners

import android.widget.ImageView

interface AdapterListener {

    fun itemClicked(pos: Int)

    fun openMovie(movieID: Int,imageView: ImageView)

}