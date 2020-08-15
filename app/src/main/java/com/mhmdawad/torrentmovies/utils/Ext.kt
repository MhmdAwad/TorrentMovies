package com.mhmdawad.torrentmovies.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MergingMediaSource
import com.mhmdawad.torrentmovies.R
import com.squareup.picasso.Picasso

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

fun SimpleExoPlayer.startPlayer(mediaSource: MergingMediaSource){
    prepare(mediaSource)
    playWhenReady = true
    playbackState
}

fun SimpleExoPlayer.addingSubtitle(mediaSource: MergingMediaSource, position: Long){
    prepare(mediaSource)
    resumePlayer()
    seekTo(position)
}

fun SimpleExoPlayer.resumePlayer(){
    playWhenReady = true
    playbackState
}

fun SimpleExoPlayer.stopPlayer(){
    playWhenReady = false
    playbackState
}

fun ImageView.downloadImage(image: String?){
    Picasso.get().load(image).placeholder(R.drawable.ic_profession).into(this)
}

fun TextView.textOrGone(s: String?){
    println("============$s=========")
    if(s!!.isEmpty())
        invisible()
    else{
        text = s
        show()
    }

}
fun TextView.addCategories(genres: List<String>) {
    var category = ""
    for(i in genres.indices){
        category += genres[i]
        if(i != genres.size-1)
            category += " | "
    }
    text = category
}
//fun Fragment.getSubtitleFile() {
//    val intent = Intent()
//    intent.type = "application/srt"
//    intent.action = Intent.ACTION_GET_CONTENT
//    startActivityForResult(Intent.createChooser(intent, "Select Subtitle"), 101)
// }

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

fun TextView.formatText(stringPath: Int, obj1: Any?, obj2: Any? = 0){
    text =String.format(resources.getString(stringPath), obj1, obj2)
}

