package com.mhmdawad.torrentmovies.utils

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun Activity.hideBottomNav() {
    findViewById<BottomNavigationView>(R.id.mainBottomNav)?.gone()

}

fun Activity.showBottomNav() {
    findViewById<BottomNavigationView>(R.id.mainBottomNav)?.show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ConstraintLayout.gone() {
    this.visibility = View.GONE
}

fun ConstraintLayout.show() {
    this.visibility = View.VISIBLE
}

fun Fragment.gone() {
    this.view?.visibility = View.GONE
}

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
}

fun SimpleExoPlayer.startPlayer(mediaSource: MergingMediaSource) {
    prepare(mediaSource)
    playWhenReady = true
    playbackState
}

fun SimpleExoPlayer.seekPlayer(
    pos: Long,
    mergeMediaSource: MergingMediaSource
) {
    startPlayer(mergeMediaSource)
    seekTo(pos)
}

fun SimpleExoPlayer.addingSubtitle(mediaSource: MergingMediaSource, position: Long) {
    prepare(mediaSource)
    resumePlayer()
    seekTo(position)
}

fun SimpleExoPlayer.resumePlayer() {
    playWhenReady = true
    playbackState
}

fun SimpleExoPlayer.stopPlayer() {
    playWhenReady = false
    playbackState
}

fun ImageView.downloadImage(image: String?, addPlaceHolder: Boolean = false) {
    Picasso.get().load(image).apply {
        if (addPlaceHolder)
            placeholder(R.drawable.ic_man).into(this@downloadImage)
        else
            into(this@downloadImage)
    }
}

fun Fragment.addNoLimitFlag() {
    activity?.window?.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun Fragment.clearNoLimitFlag() {
    activity?.window?.clearFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun TextView.textOrGone(s: String?) {
    if (s!!.isEmpty())
        invisible()
    else {
        text = s
        show()
    }

}

fun TextView.addCategories(genres: List<String>) {
    var category = ""
    for (i in genres.indices) {
        category += genres[i]
        if (i != genres.size - 1)
            category += " | "
    }
    text = category
}


fun Fragment.hideSystemUI(): Int {
    return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE)
}

fun Fragment.showSystemUI(): Int {
    return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}

fun TextView.formatText(stringPath: Int, obj1: Any?, obj2: Any? = 0) {
    text = String.format(resources.getString(stringPath), obj1, obj2)
}


suspend fun MutableList<MoviesItem>.distinctList(list: List<MoviesItem>) {
    withContext(Dispatchers.IO) {
        this@distinctList.apply {
            addAll(list)
            val desList = distinctBy { it.id }
            clear()
            addAll(desList)
        }
    }
}

fun List<MoviesItem>.changeCategory(category: String) {
    for (i in this) {
        i.category = category
        i.timeSaved = System.currentTimeMillis()
    }
}

fun RecyclerView.addDividers() {
    addItemDecoration(
        DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
    )
}

fun MutableList<MoviesItem>.addList(list: List<MoviesItem>) {
    this.apply {
        clear()
        addAll(list)
    }
}

fun Movie.convertToFavorite(): FavoriteMovie {
    return FavoriteMovie(
        smallCoverImage,
        year,
        descriptionFull,
        rating,
        largeCoverImage,
        titleLong,
        language,
        ytTrailerCode,
        title,
        cast,
        mpaRating,
        genres,
        largeScreenshotImage1,
        titleEnglish,
        id,
        slug,
        largeScreenshotImage3,
        largeScreenshotImage2,
        likeCount,
        dateUploaded,
        descriptionIntro,
        runtime,
        url,
        imdbCode,
        downloadCount,
        backgroundImage,
        mediumScreenshotImage3,
        mediumScreenshotImage2,
        torrents,
        mediumScreenshotImage1,
        dateUploadedUnix,
        backgroundImageOriginal,
        mediumCoverImage
    )
}


