package com.mhmdawad.torrentmovies.data.model

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

data class MoviesItem(

	@field:SerializedName("small_cover_image")
	val smallCoverImage: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("description_full")
	val descriptionFull: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("large_cover_image")
	val largeCoverImage: String? = null,

	@field:SerializedName("title_long")
	val titleLong: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("yt_trailer_code")
	val ytTrailerCode: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("mpa_rating")
	val mpaRating: String? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("date_uploaded")
	val dateUploaded: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("imdb_code")
	val imdbCode: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("torrents")
	val torrents: List<TorrentsItem?>? = null,

	@field:SerializedName("date_uploaded_unix")
	val dateUploadedUnix: Int? = null,

	@field:SerializedName("background_image_original")
	val backgroundImageOriginal: String? = null,

	@field:SerializedName("medium_cover_image")
	val mediumCoverImage: String? = null
)

data class TorrentsItem(

	@field:SerializedName("size_bytes")
	val sizeBytes: Long? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("seeds")
	val seeds: Int? = null,

	@field:SerializedName("date_uploaded")
	val dateUploaded: String? = null,

	@field:SerializedName("peers")
	val peers: Int? = null,

	@field:SerializedName("date_uploaded_unix")
	val dateUploadedUnix: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("quality")
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
