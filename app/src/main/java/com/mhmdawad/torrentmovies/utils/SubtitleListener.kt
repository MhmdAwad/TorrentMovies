package com.mhmdawad.torrentmovies.utils

import com.masterwok.opensubtitlesandroid.models.OpenSubtitleItem

interface SubtitleListener {

    fun onSubtitleClicked(subtitle: OpenSubtitleItem)
}