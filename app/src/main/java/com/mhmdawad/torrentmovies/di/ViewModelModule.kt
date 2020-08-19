package com.mhmdawad.torrentmovies.di

import com.mhmdawad.torrentmovies.ui.fragments.explore.ExploreViewModel
import com.mhmdawad.torrentmovies.ui.fragments.details.DetailsViewModel
import com.mhmdawad.torrentmovies.ui.fragments.favorites.FavoriteViewModel
import com.mhmdawad.torrentmovies.ui.fragments.rank.RankViewModel
import com.mhmdawad.torrentmovies.ui.fragments.stream.StreamViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ExploreViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { StreamViewModel(get(), get()) }
    viewModel { RankViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}