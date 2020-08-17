package com.mhmdawad.torrentmovies.data.source.cache

import com.mhmdawad.torrentmovies.data.model.MoviesItem

interface ICacheSource {

    suspend fun getCacheMoviesList():List<MoviesItem>
    suspend fun saveCacheMoviesList(list: List<MoviesItem>): Long
    suspend fun deleteAllCacheMovies()
}