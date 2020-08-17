package com.mhmdawad.torrentmovies.ui

import com.mhmdawad.torrentmovies.data.model.MovieDetails
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.data.source.network.INetworkSource

class MainRepository(private val networkSource: INetworkSource) {

    suspend fun getMovieSearch(search: String,page: Int):MoviesResponse{
        return networkSource.searchMovie(search, page)
    }

    suspend fun getMoviesCategory(category: String, page: Int):MoviesResponse{
        return networkSource.getMoviesCategory(category, page)
    }

    suspend fun getMovieDetails(id: Int): MovieDetails {
        return networkSource.movieDetails(id)
    }

    suspend fun getRankingMovies(page: Int): MoviesResponse {
        return networkSource.rankMovies(page)
    }



}