package com.mhmdawad.torrentmovies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FavoriteMovie(

    @field:SerializedName("small_cover_image")
    @ColumnInfo(name = "small_cover_image")
    val smallCoverImage: String?,

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
    val ytTrailerCode: String?,

    @field:SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @field:SerializedName("cast")
    @ColumnInfo(name = "cast")
    var cast: List<CastItem>?,

    @field:SerializedName("mpa_rating")
    @ColumnInfo(name = "mpa_rating")
    val mpaRating: String?,

    @field:SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<String>?,

    @field:SerializedName("large_screenshot_image1")
    @ColumnInfo(name = "large_screenshot_image1")
    val largeScreenshotImage1: String?,

    @field:SerializedName("title_english")
    @ColumnInfo(name = "title_english")
    val titleEnglish: String?,

    @field:SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @field:SerializedName("slug")
    @ColumnInfo(name = "slug")
    val slug: String?,

    @field:SerializedName("large_screenshot_image3")
    @ColumnInfo(name = "large_screenshot_image3")
    val largeScreenshotImage3: String?,

    @field:SerializedName("large_screenshot_image2")
    @ColumnInfo(name = "large_screenshot_image2")
    val largeScreenshotImage2: String?,

    @field:SerializedName("like_count")
    @ColumnInfo(name = "like_count")
    val likeCount: Int?,

    @field:SerializedName("date_uploaded")
    @ColumnInfo(name = "date_uploaded")
    val dateUploaded: String?,

    @field:SerializedName("description_intro")
    @ColumnInfo(name = "description_intro")
    val descriptionIntro: String?,

    @field:SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @field:SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,

    @field:SerializedName("imdb_code")
    @ColumnInfo(name = "imdb_code")
    val imdbCode: String?,

    @field:SerializedName("download_count")
    @ColumnInfo(name = "download_count")
    val downloadCount: Int?,

    @field:SerializedName("background_image")
    @ColumnInfo(name = "background_image")
    val backgroundImage: String?,

    @field:SerializedName("medium_screenshot_image3")
    @ColumnInfo(name = "medium_screenshot_image3")
    val mediumScreenshotImage3: String?,

    @field:SerializedName("medium_screenshot_image2")
    @ColumnInfo(name = "medium_screenshot_image2")
    val mediumScreenshotImage2: String?,

    @field:SerializedName("torrents")
    @ColumnInfo(name = "torrents")
    val torrents: List<TorrentsDetails>?,

    @field:SerializedName("medium_screenshot_image1")
    @ColumnInfo(name = "medium_screenshot_image1")
    val mediumScreenshotImage1: String?,

    @field:SerializedName("date_uploaded_unix")
    @ColumnInfo(name = "date_uploaded_unix")
    val dateUploadedUnix: Int?,

    @field:SerializedName("background_image_original")
    @ColumnInfo(name = "background_image_original")
    val backgroundImageOriginal: String?,

    @field:SerializedName("medium_cover_image")
    @ColumnInfo(name = "medium_cover_image")
    val mediumCoverImage: String?
)
