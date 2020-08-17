package com.mhmdawad.torrentmovies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
	val smallCoverImage: String? = null,

	@field:SerializedName("year")
	@ColumnInfo(name = "year")
	val year: Int? = null,

	@field:SerializedName("description_full")
	@ColumnInfo(name = "description_full")
	val descriptionFull: String? = null,

	@field:SerializedName("rating")
	@ColumnInfo(name = "rating")
	val rating: Double? = null,

	@field:SerializedName("large_cover_image")
	@ColumnInfo(name = "large_cover_image")
	val largeCoverImage: String? = null,

	@field:SerializedName("title_long")
	@ColumnInfo(name = "title_long")
	val titleLong: String? = null,

	@field:SerializedName("language")
	@ColumnInfo(name = "language")
	val language: String? = null,

	@field:SerializedName("yt_trailer_code")
	@ColumnInfo(name = "yt_trailer_code")
	val ytTrailerCode: String? = null,

	@field:SerializedName("title")
	@ColumnInfo(name = "title")
	val title: String? = null,

	@field:SerializedName("mpa_rating")
	@ColumnInfo(name = "mpa_rating")
	val mpaRating: String? = null,

	@field:SerializedName("genres")
	@ColumnInfo(name = "genres")
	val genres: List<String>? = null,

	@field:SerializedName("title_english")
	@ColumnInfo(name = "title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("id")
	@PrimaryKey
	val id: Int? = null,

	@field:SerializedName("state")
	@ColumnInfo(name = "state")
	val state: String? = null,

	@field:SerializedName("slug")
	@ColumnInfo(name = "slug")
	val slug: String? = null,

	@field:SerializedName("summary")
	@ColumnInfo(name = "summary")
	val summary: String? = null,

	@field:SerializedName("date_uploaded")
	@ColumnInfo(name = "date_uploaded")
	val dateUploaded: String? = null,

	@field:SerializedName("runtime")
	@ColumnInfo(name = "runtime")
	val runtime: Int? = null,

	@field:SerializedName("synopsis")
	@ColumnInfo(name = "synopsis")
	val synopsis: String? = null,

	@field:SerializedName("url")
	@ColumnInfo(name = "url")
	val url: String? = null,

	@field:SerializedName("imdb_code")
	@ColumnInfo(name = "imdb_code")
	val imdbCode: String? = null,

	@field:SerializedName("background_image")
	@ColumnInfo(name = "background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("torrents")
	@ColumnInfo(name = "torrents")
	val torrents: List<TorrentsItem?>? = null,

	@field:SerializedName("date_uploaded_unix")
	@ColumnInfo(name = "date_uploaded_unix")
	val dateUploadedUnix: Int? = null,

	@field:SerializedName("background_image_original")
	@ColumnInfo(name = "background_image_original")
	val backgroundImageOriginal: String? = null,

	@field:SerializedName("medium_cover_image")
	@ColumnInfo(name = "medium_cover_image")
	val mediumCoverImage: String? = null
)

data class TorrentsItem(

	@field:SerializedName("size_bytes")
	@ColumnInfo(name = "size_bytes")
	val sizeBytes: Long? = null,

	@field:SerializedName("size")
	@ColumnInfo(name = "size")
	val size: String? = null,

	@field:SerializedName("seeds")
	@ColumnInfo(name = "seeds")
	val seeds: Int? = null,

	@field:SerializedName("date_uploaded")
	@ColumnInfo(name = "date_uploaded")
	val dateUploaded: String? = null,

	@field:SerializedName("peers")
	@ColumnInfo(name = "peers")
	val peers: Int? = null,

	@field:SerializedName("date_uploaded_unix")
	@ColumnInfo(name = "date_uploaded_unix")
	val dateUploadedUnix: Int? = null,

	@field:SerializedName("type")
	@ColumnInfo(name = "type")
	val type: String? = null,

	@field:SerializedName("url")
	@ColumnInfo(name = "url")
	val url: String? = null,

	@field:SerializedName("hash")
	@ColumnInfo(name = "hash")
	val hash: String? = null,

	@field:SerializedName("quality")
	@ColumnInfo(name = "quality")
	val quality: String? = null
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
