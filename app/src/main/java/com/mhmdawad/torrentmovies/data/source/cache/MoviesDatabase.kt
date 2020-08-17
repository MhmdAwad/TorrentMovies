package com.mhmdawad.torrentmovies.data.source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mhmdawad.torrentmovies.data.model.*

@Database(entities = [MoviesItem::class, Movie::class], version = 1, exportSchema = false)
@TypeConverters(StringTypeConverter::class, CastTypeConverter::class,
    TorrentTypeConverter::class, TorrentsDetailsTypeConverter::class)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

}