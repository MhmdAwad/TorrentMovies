package com.mhmdawad.torrentmovies.utils

import android.app.Activity
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView

fun View.show() {
    this.visibility = View.VISIBLE
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

fun SimpleExoPlayer.startPlayer(mediaSource: ExtractorMediaSource){
    this.prepare(mediaSource)
    this.playWhenReady = true
    this.playbackState
}

fun SimpleExoPlayer.resumePlayer(){
    this.playWhenReady = true
    this.playbackState
}

fun SimpleExoPlayer.stopPlayer(){
    this.playWhenReady = false
    this.playbackState
}
