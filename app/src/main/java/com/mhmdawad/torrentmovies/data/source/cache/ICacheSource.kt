package com.mhmdawad.torrentmovies.data.source.cache

import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem

interface ICacheSource {

    suspend fun getCacheMoviesList(category: String, limit: Int, page: Int):List<MoviesItem>
    suspend fun saveCacheMoviesList(list: List<MoviesItem>)
    suspend fun deleteAllCacheMovies()
    suspend fun getSpecificMovie(id: Int): Movie
    suspend fun saveSpecificMovie(movie: Movie)
}