package com.mhmdawad.torrentmovies.utils.RVListeners

import com.masterwok.opensubtitlesandroid.models.OpenSubtitleItem

interface SubtitleListener {

    fun onSubtitleClicked(subtitle: OpenSubtitleItem)
}