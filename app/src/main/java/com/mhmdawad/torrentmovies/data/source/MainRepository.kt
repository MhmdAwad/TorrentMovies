package com.mhmdawad.torrentmovies.data.source

import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.data.source.cache.ICacheSource
import com.mhmdawad.torrentmovies.data.source.network.INetworkSource
import com.mhmdawad.torrentmovies.utils.changeCategory
import com.mhmdawad.torrentmovies.utils.convertToFavorite

class MainRepository(
    private val networkSource: INetworkSource,
    private val cacheSource: ICacheSource
) {
    private var firstNetworkLoad = true

    private suspend fun getNetworkCategory(category: String, page: Int) {
        val result = networkSource.getMoviesCategory(category, page)
        result.data?.movies?.apply {
            if (this.isNotEmpty()) {
                deleteLastCache()
                changeCategory(category)
                cacheSource.saveCacheMoviesList(this)
            }
        }
    }
    private suspend fun deleteLastCache(){
        if(firstNetworkLoad){
            cacheSource.deleteAllCacheMovies()
            firstNetworkLoad = false
        }
    }

    suspend fun getCacheCategory(category: String, page: Int): List<MoviesItem> {
        try {
            getNetworkCategory(category, page)
        } finally {
            val result = cacheSource.getCacheMoviesList(category, 20, page.times(10))
            if (result.isEmpty())
                throw IllegalArgumentException()
            return result
        }
    }

    suspend fun getNetworkSearch(search: String, page: Int): MoviesResponse {
        return networkSource.searchMovie(search, page)
    }

    private suspend fun getNetworkDetails(id: Int): Movie {
        val result = networkSource.movieDetails(id)
        with(result.data?.movie!!) {
            if (this.cast == null)
                this.cast = emptyList()
            cacheSource.saveSpecificMovie(this)
            return this
        }
    }

    suspend fun getCacheDetails(id: Int): Movie {
        var result: Movie? = cacheSource.getSpecificMovie(id)
        if (result == null)
            result = getNetworkDetails(id)
        return result
    }


    private suspend fun getCacheRanking(page: Int): List<MoviesItem> =
        cacheSource.getRankMovies(20, page.times(10))


    suspend fun getNetworkRanking(page: Int): List<MoviesItem> {
        var result = emptyList<MoviesItem>()
        try {
            result = networkSource.rankMovies(page).data!!.movies
        } finally {
            if (result.isEmpty())
                result = getCacheRanking(page)
            if (result.isEmpty())
                throw IllegalArgumentException()
            else
                return result
        }
    }

    suspend fun saveMovieToFav(movie: Movie) {
        cacheSource.saveFavMovie(movie.convertToFavorite())
        checkFavMovieExist(movie.id!!)
    }

    suspend fun getAllFavMovie(): List<FavoriteMovie> {
        val result = cacheSource.getAllFavMovies()
        if (result.isEmpty())
            throw IllegalArgumentException()
        return result
    }

    suspend fun deleteSpecificFavMovie(id: Int) {
        cacheSource.deleteFavMovie(id)
    }

    suspend fun checkFavMovieExist(id: Int): Boolean =
        cacheSource.checkFavMovieExist(id)

}