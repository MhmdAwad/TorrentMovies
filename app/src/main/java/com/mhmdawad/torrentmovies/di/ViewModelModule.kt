package com.mhmdawad.torrentmovies.di

import com.mhmdawad.torrentmovies.ui.fragments.home.HomeViewModel
import com.mhmdawad.torrentmovies.ui.fragments.details.DetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

}