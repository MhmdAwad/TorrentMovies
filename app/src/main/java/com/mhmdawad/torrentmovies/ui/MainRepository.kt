package com.mhmdawad.torrentmovies.ui

import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.data.source.cache.ICacheSource
import com.mhmdawad.torrentmovies.data.source.network.INetworkSource
import com.mhmdawad.torrentmovies.utils.changeCategory
import java.lang.IllegalArgumentException

class MainRepository(
    private val networkSource: INetworkSource,
    private val cacheSource: ICacheSource
) {

    private suspend fun getNetworkCategory(category: String, page: Int) {
        val result = networkSource.getMoviesCategory(category, page)
        result.data?.movies?.apply {
            changeCategory(category)
            saveMoviesCategory(this)
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
        var result = cacheSource.getSpecificMovie(id)
        if (result == null)
            result = getNetworkDetails(id)

        return result
    }

    suspend fun getNetworkRanking(page: Int): MoviesResponse {
        return networkSource.rankMovies(page)
    }


    private suspend fun saveMoviesCategory(list: List<MoviesItem>) {
        cacheSource.saveCacheMoviesList(list)
    }

}