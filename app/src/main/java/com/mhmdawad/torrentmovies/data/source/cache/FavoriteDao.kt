package com.mhmdawad.torrentmovies.data.source.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.data.model.Movie

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM FavoriteMovie")
    suspend fun getAllFavMovies(): List<FavoriteMovie>

    @Query("DELETE FROM FavoriteMovie")
    suspend fun deleteAllFavMovies()

}