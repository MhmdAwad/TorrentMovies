package com.mhmdawad.torrentmovies.utils.RVListeners

import android.widget.ImageView

interface AdapterListener {

    fun itemClicked(pos: Int)

    fun openMovie(movieID: Int,imageView: ImageView)

}