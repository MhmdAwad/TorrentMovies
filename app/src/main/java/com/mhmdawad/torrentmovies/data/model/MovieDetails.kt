package com.mhmdawad.torrentmovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("data")
	val data: DataDetails? = null,

	@field:SerializedName("@meta")
	val meta: MetaDetails? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class TorrentsDetails(

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

data class Movie(

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

	@field:SerializedName("cast")
	val cast: List<CastItem> = emptyList(),

	@field:SerializedName("mpa_rating")
	val mpaRating: String? = null,

	@field:SerializedName("genres")
	val genres: List<String>? = null,

	@field:SerializedName("large_screenshot_image1")
	val largeScreenshotImage1: String? = null,

	@field:SerializedName("title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("large_screenshot_image3")
	val largeScreenshotImage3: String? = null,

	@field:SerializedName("large_screenshot_image2")
	val largeScreenshotImage2: String? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("date_uploaded")
	val dateUploaded: String? = null,

	@field:SerializedName("description_intro")
	val descriptionIntro: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("imdb_code")
	val imdbCode: String? = null,

	@field:SerializedName("download_count")
	val downloadCount: Int? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("medium_screenshot_image3")
	val mediumScreenshotImage3: String? = null,

	@field:SerializedName("medium_screenshot_image2")
	val mediumScreenshotImage2: String? = null,

	@field:SerializedName("torrents")
	val torrents: List<TorrentsDetails>? = null,

	@field:SerializedName("medium_screenshot_image1")
	val mediumScreenshotImage1: String? = null,

	@field:SerializedName("date_uploaded_unix")
	val dateUploadedUnix: Int? = null,

	@field:SerializedName("background_image_original")
	val backgroundImageOriginal: String? = null,

	@field:SerializedName("medium_cover_image")
	val mediumCoverImage: String? = null
)

data class MetaDetails(

	@field:SerializedName("server_time")
	val serverTime: Int? = null,

	@field:SerializedName("server_timezone")
	val serverTimezone: String? = null,

	@field:SerializedName("api_version")
	val apiVersion: Int? = null,

	@field:SerializedName("execution_time")
	val executionTime: String? = null
)

data class DataDetails(

	@field:SerializedName("movie")
	val movie: Movie? = null
)

data class CastItem(

	@field:SerializedName("character_name")
	val characterName: String? = null,

	@field:SerializedName("url_small_image")
	val urlSmallImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("imdb_code")
	val imdbCode: String? = null
)
