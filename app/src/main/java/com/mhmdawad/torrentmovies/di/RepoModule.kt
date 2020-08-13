package com.mhmdawad.torrentmovies.di


import com.mhmdawad.torrentmovies.data.source.INetworkResource
import com.mhmdawad.torrentmovies.data.source.NetworkResourceImpl
import com.mhmdawad.torrentmovies.ui.MainRepository
import org.koin.dsl.module

val repoModule = module {

    single { MainRepository(get()) }

    factory<INetworkResource> { NetworkResourceImpl(get()) }


}