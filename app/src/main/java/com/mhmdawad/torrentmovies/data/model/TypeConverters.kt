package com.mhmdawad.torrentmovies.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class TorrentTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<TorrentsItem> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<TorrentsItem>>() {

        }.type

        return gson.fromJson<List<TorrentsItem>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<TorrentsItem>): String {
        return gson.toJson(someObjects)
    }
}
class TorrentsDetailsTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<TorrentsDetails> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<TorrentsDetails>>() {

        }.type

        return gson.fromJson<List<TorrentsDetails>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<TorrentsDetails>): String {
        return gson.toJson(someObjects)
    }
}
class StringTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<String> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>() {

        }.type

        return gson.fromJson<List<String>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<String>): String {
        return gson.toJson(someObjects)
    }
}

class CastTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<CastItem> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<CastItem>>() {

        }.type

        return gson.fromJson<List<CastItem>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<CastItem>): String {
        return gson.toJson(someObjects)
    }
}
