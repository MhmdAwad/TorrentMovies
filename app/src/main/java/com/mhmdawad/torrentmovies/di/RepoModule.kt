package com.mhmdawad.torrentmovies.di


import com.mhmdawad.torrentmovies.data.source.cache.CacheSourceImpl
import com.mhmdawad.torrentmovies.data.source.cache.ICacheSource
import com.mhmdawad.torrentmovies.data.source.network.INetworkSource
import com.mhmdawad.torrentmovies.data.source.network.NetworkSourceImpl
import com.mhmdawad.torrentmovies.data.source.MainRepository
import org.koin.dsl.module

val repoModule = module {

    single {
        MainRepository(
            get(),
            get()
        )
    }

    factory<INetworkSource> {
        NetworkSourceImpl(
            get()
        )
    }

    factory<ICacheSource> {
        CacheSourceImpl(
            get()
        )
    }


}