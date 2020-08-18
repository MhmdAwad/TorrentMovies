package com.mhmdawad.torrentmovies.data.source.cache

import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem

class CacheSourceImpl(private val moviesDao: MoviesDao,  private val favoriteDao: FavoriteDao) : ICacheSource {

    override suspend fun getCacheMoviesList(category: String, limit: Int, page: Int): List<MoviesItem> =
        moviesDao.getAllMovies(category, limit, page)

    override suspend fun saveCacheMoviesList(list: List<MoviesItem>) =
        moviesDao.saveMovies(list)

    override suspend fun deleteAllCacheMovies() =
        moviesDao.deleteMoviesList()

    override suspend fun getSpecificMovie(id: Int): Movie =
        moviesDao.getSpecificMovie(id)

    override suspend fun saveSpecificMovie(movie: Movie) =
        moviesDao.saveSpecificMovie(movie)

    override suspend fun saveFavMovie(movie: FavoriteMovie) =
        favoriteDao.saveFavoriteMovie(movie)

    override suspend fun getAllFavMovies(): List<FavoriteMovie> =
        favoriteDao.getAllFavMovies()

    override suspend fun deleteFavMovie(id: Int) =
        favoriteDao.deleteFavMovie(id)

    override suspend fun checkFavMovieExist(id: Int) =
        favoriteDao.checkMovieExist(id)
}