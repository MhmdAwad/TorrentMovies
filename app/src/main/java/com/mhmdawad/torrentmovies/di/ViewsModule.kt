package com.mhmdawad.torrentmovies.di

import android.view.animation.AnimationUtils
import android.view.animation.GridLayoutAnimationController
import android.view.animation.LayoutAnimationController
import com.github.se_bastiaan.torrentstream.TorrentOptions
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.mhmdawad.torrentmovies.R
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val viewsModule = module {
    // val animation =
    //            AnimationUtils.loadAnimation(activity, R.anim.down_to_up)
    //        val controller = GridLayoutAnimationController(animation)
    //        controller.order = LayoutAnimationController.ORDER_NORMAL
    //        controller.direction = GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT or
    //                GridLayoutAnimationController.DIRECTION_TOP_TO_BOTTOM
    //        controller.columnDelay = 0.15f
    //        controller.rowDelay = 0.15f

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
            .autoDownload(true)
            .build()
    }

    factory {
        TorrentStream.init(get())
    }

}