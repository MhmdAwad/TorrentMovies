package com.mhmdawad.torrentmovies.utils

import com.mhmdawad.torrentmovies.data.model.TorrentsDetails

interface QualityListener {

    fun selectQuality(movieUrl: String, movieName:String)
}