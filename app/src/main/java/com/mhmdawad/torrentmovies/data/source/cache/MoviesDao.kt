package com.mhmdawad.torrentmovies.data.source.cache


import androidx.room.*
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MoviesItem>)

    @Query("SELECT * FROM MoviesItem WHERE category= :category ORDER BY timeSaved LIMIT :limit OFFSET :page ")
    suspend fun getAllMovies(category: String, limit:Int, page: Int): List<MoviesItem>

    @Query("DELETE FROM MoviesItem")
    suspend fun deleteMoviesList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSpecificMovie(movie: Movie)

    @Query("SELECT * FROM movie  WHERE id = :id LIMIT 1")
    suspend fun getSpecificMovie(id: Int): Movie

}