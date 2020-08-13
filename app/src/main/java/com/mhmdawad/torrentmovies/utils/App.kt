package com.mhmdawad.torrentmovies.utils

import android.app.Application
import com.mhmdawad.torrentmovies.di.networkModule
import com.mhmdawad.torrentmovies.di.repoModule
import com.mhmdawad.torrentmovies.di.viewModelModule
import com.mhmdawad.torrentmovies.di.viewsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repoModule, viewModelModule, viewsModule))
        }
    }
}