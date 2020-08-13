package com.mhmdawad.torrentmovies.ui

import com.mhmdawad.torrentmovies.data.model.MovieDetails
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.data.source.INetworkResource

class MainRepository(private val networkResource: INetworkResource) {

    suspend fun getMovieSearch(search: String,page: Int):MoviesResponse{
        return networkResource.searchMovie(search, page)
    }

    suspend fun getMoviesCategory(category: String, page: Int):MoviesResponse{
        return networkResource.getMoviesCategory(category, page)
    }

    suspend fun getMovieDetails(id: Int): MovieDetails {
        return networkResource.movieDetails(id)
    }

}