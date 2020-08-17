package com.mhmdawad.torrentmovies.di

import android.view.animation.AnimationUtils
import android.view.animation.GridLayoutAnimationController
import android.view.animation.LayoutAnimationController
import com.github.se_bastiaan.torrentstream.TorrentOptions
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.masterwok.opensubtitlesandroid.services.OpenSubtitlesService
import com.mhmdawad.torrentmovies.R
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val viewsModule = module {


    single { AnimationUtils.loadAnimation(get(), R.anim.down_to_up) }

    single { GridLayoutAnimationController(get()).apply {
        order = LayoutAnimationController.ORDER_NORMAL
        direction = GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT or
                GridLayoutAnimationController.DIRECTION_TOP_TO_BOTTOM
        columnDelay = 0.15f
        rowDelay = 0.15f
    }
    }

    factory {
        ExoPlayerFactory.newSimpleInstance(get())
    }
    single {
        DefaultDataSourceFactory(get(), Util.getUserAgent(get(), "appName"))
    }

    single {
        TorrentOptions.Builder()
            .saveLocation(androidApplication().getExternalFilesDir(null)?.absolutePath)
            .removeFilesAfterStop(true)
            .build()
    }

    factory {
        TorrentStream.init(get())
    }

    single {
        Format.createTextSampleFormat(
            null, MimeTypes.APPLICATION_SUBRIP,
            null, Format.NO_VALUE, Format.NO_VALUE, "en", null, Format.OFFSET_SAMPLE_RELATIVE)
    }

    single {
        OpenSubtitlesService()
    }

}