package com.mhmdawad.torrentmovies.data.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

data class MoviesResponse(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("@meta")
	val meta: Meta? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Meta(

	@field:SerializedName("server_time")
	val serverTime: Int? = null,

	@field:SerializedName("server_timezone")
	val serverTimezone: String? = null,

	@field:SerializedName("api_version")
	val apiVersion: Int? = null,

	@field:SerializedName("execution_time")
	val executionTime: String? = null
)

@Entity
data class MoviesItem(

	@field:SerializedName("small_cover_image")
	@ColumnInfo(name = "small_cover_image")
	val smallCoverImage: String?,

	@ColumnInfo(name = "category")
	var category: String? = "",

	@ColumnInfo(name = "timeSaved")
	val timeSaved: Long = System.currentTimeMillis(),

	@field:SerializedName("year")
	@ColumnInfo(name = "year")
	val year: Int?,

	@field:SerializedName("description_full")
	@ColumnInfo(name = "description_full")
	val descriptionFull: String?,

	@field:SerializedName("rating")
	@ColumnInfo(name = "rating")
	val rating: Double?,

	@field:SerializedName("large_cover_image")
	@ColumnInfo(name = "large_cover_image")
	val largeCoverImage: String?,

	@field:SerializedName("title_long")
	@ColumnInfo(name = "title_long")
	val titleLong: String?,

	@field:SerializedName("language")
	@ColumnInfo(name = "language")
	val language: String?,

	@field:SerializedName("yt_trailer_code")
	@ColumnInfo(name = "yt_trailer_code")
	val ytTrailerCode: String? ,

	@field:SerializedName("title")
	@ColumnInfo(name = "title")
	val title: String? ,

	@field:SerializedName("mpa_rating")
	@ColumnInfo(name = "mpa_rating")
	val mpaRating: String? ,

	@field:SerializedName("genres")
	@ColumnInfo(name = "genres")
	val genres: List<String>? ,

	@field:SerializedName("title_english")
	@ColumnInfo(name = "title_english")
	val titleEnglish: String? ,

	@field:SerializedName("id")
	@PrimaryKey
	val id: Int?,

	@field:SerializedName("state")
	@ColumnInfo(name = "state")
	val state: String? ,

	@field:SerializedName("slug")
	@ColumnInfo(name = "slug")
	val slug: String? ,

	@field:SerializedName("summary")
	@ColumnInfo(name = "summary")
	val summary: String? ,

	@field:SerializedName("date_uploaded")
	@ColumnInfo(name = "date_uploaded")
	val dateUploaded: String? ,

	@field:SerializedName("runtime")
	@ColumnInfo(name = "runtime")
	val runtime: Int? ,

	@field:SerializedName("synopsis")
	@ColumnInfo(name = "synopsis")
	val synopsis: String? ,

	@field:SerializedName("url")
	@ColumnInfo(name = "url")
	val url: String? ,

	@field:SerializedName("imdb_code")
	@ColumnInfo(name = "imdb_code")
	val imdbCode: String? ,

	@field:SerializedName("background_image")
	@ColumnInfo(name = "background_image")
	val backgroundImage: String? ,

	@field:SerializedName("torrents")
	@ColumnInfo(name = "torrents")
	val torrents: List<TorrentsItem>? ,

	@field:SerializedName("date_uploaded_unix")
	@ColumnInfo(name = "date_uploaded_unix")
	val dateUploadedUnix: Int? ,

	@field:SerializedName("background_image_original")
	@ColumnInfo(name = "background_image_original")
	val backgroundImageOriginal: String?,

	@field:SerializedName("medium_cover_image")
	@ColumnInfo(name = "medium_cover_image")
	val mediumCoverImage: String?
)

data class TorrentsItem(

	@field:SerializedName("size_bytes")
	@ColumnInfo(name = "size_bytes")
	val sizeBytes: Long? ,

	@field:SerializedName("size")
	@ColumnInfo(name = "size")
	val size: String? ,

	@field:SerializedName("seeds")
	@ColumnInfo(name = "seeds")
	val seeds: Int? ,

	@field:SerializedName("date_uploaded")
	@ColumnInfo(name = "date_uploaded")
	val dateUploaded: String? ,

	@field:SerializedName("peers")
	@ColumnInfo(name = "peers")
	val peers: Int? ,

	@field:SerializedName("date_uploaded_unix")
	@ColumnInfo(name = "date_uploaded_unix")
	val dateUploadedUnix: Int? ,

	@field:SerializedName("type")
	@ColumnInfo(name = "type")
	val type: String? ,

	@field:SerializedName("url")
	@ColumnInfo(name = "url")
	val url: String? ,

	@field:SerializedName("hash")
	@ColumnInfo(name = "hash")
	val hash: String? ,

	@field:SerializedName("quality")
	@ColumnInfo(name = "quality")
	val quality: String?
)

data class Data(

	@field:SerializedName("movies")
	val movies: List<MoviesItem> = emptyList(),

	@field:SerializedName("page_number")
	val pageNumber: Int? = null,

	@field:SerializedName("movie_count")
	val movieCount: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null
)