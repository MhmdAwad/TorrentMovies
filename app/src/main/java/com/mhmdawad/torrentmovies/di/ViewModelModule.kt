package com.mhmdawad.torrentmovies.di

import com.mhmdawad.torrentmovies.ui.fragments.home.HomeViewModel
import com.mhmdawad.torrentmovies.ui.fragments.details.DetailsViewModel
import com.mhmdawad.torrentmovies.ui.fragments.rank.RankViewModel
import com.mhmdawad.torrentmovies.ui.fragments.stream.StreamViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { StreamViewModel(get(), get()) }
    viewModel { RankViewModel(get()) }
}