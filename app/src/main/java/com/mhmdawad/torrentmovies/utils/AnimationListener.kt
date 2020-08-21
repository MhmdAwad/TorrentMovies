package com.mhmdawad.torrentmovies.utils

import android.view.animation.Animation

abstract class AnimationListener : Animation.AnimationListener{
    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        animationFinished()
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    abstract fun animationFinished()
}