package com.mhmdawad.torrentmovies.data.source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhmdawad.torrentmovies.data.model.MoviesItem

@Database(entities = [MoviesItem::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}