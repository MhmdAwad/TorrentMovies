package com.mhmdawad.torrentmovies.di

import androidx.room.Room
import com.mhmdawad.torrentmovies.data.source.cache.MoviesDao
import com.mhmdawad.torrentmovies.data.source.cache.MoviesDatabase
import com.mhmdawad.torrentmovies.utils.Constants
import org.koin.dsl.module

val cacheModule = module {

    single {
        Room.databaseBuilder(get(), MoviesDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    single { get<MoviesDatabase>().getMoviesDao() }
}