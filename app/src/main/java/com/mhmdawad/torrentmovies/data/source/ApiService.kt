package com.mhmdawad.torrentmovies.data.source

import com.mhmdawad.torrentmovies.data.model.MovieDetails
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list_movies.json")
    suspend fun searchInMovie(@Query("query_term") search: String, @Query("page") page: Int): MoviesResponse

    @GET("list_movies.json")
    suspend fun getMoviesCategoryList(@Query("genre") category: String, @Query("page") page: Int) : MoviesResponse

    @GET("movie_details.json?with_images=true&with_cast=true")
    suspend fun getMovieDetails(@Query("movie_id") id: Int) : MovieDetails

}