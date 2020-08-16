package com.mhmdawad.torrentmovies.data.source

import com.mhmdawad.torrentmovies.data.model.MovieDetails
import com.mhmdawad.torrentmovies.data.model.MoviesResponse

interface INetworkResource {

    suspend fun searchMovie(search: String,page: Int): MoviesResponse
    suspend fun getMoviesCategory(category: String, page: Int): MoviesResponse
    suspend fun movieDetails(id: Int): MovieDetails
    suspend fun rankMovies(page: Int): MoviesResponse

}