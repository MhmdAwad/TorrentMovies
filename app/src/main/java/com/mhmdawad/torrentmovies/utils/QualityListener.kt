package com.mhmdawad.torrentmovies.utils

import com.mhmdawad.torrentmovies.data.model.TorrentsDetails

interface QualityListener {

    fun selectQuality(url: String)
}