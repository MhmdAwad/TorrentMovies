package com.mhmdawad.torrentmovies.data.source.cache

import com.mhmdawad.torrentmovies.data.model.MoviesItem

class CacheSourceImpl(private val moviesDao: MoviesDao) : ICacheSource {

    override suspend fun getCacheMoviesList(): List<MoviesItem> =
        moviesDao.getAllMovies()

    override suspend fun saveCacheMoviesList(list: List<MoviesItem>): Long =
        moviesDao.saveMovies(list)

    override suspend fun deleteAllCacheMovies() =
        moviesDao.deleteMoviesList()
}