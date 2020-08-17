package com.mhmdawad.torrentmovies.data.source.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhmdawad.torrentmovies.data.model.MoviesItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MoviesItem>): Long

    @Query("SELECT * FROM MoviesItem")
    suspend fun getAllMovies(): List<MoviesItem>

    @Query("DELETE FROM MoviesItem")
    suspend fun deleteMoviesList()

}