package com.mhmdawad.torrentmovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.AnimationListener
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fadeInAnim = AnimationUtils.loadAnimation(this.applicationContext, R.anim.fade_in)
        appNameSplash.startAnimation(fadeInAnim)

        fadeInAnim.setAnimationListener(object: AnimationListener(){
            override fun animationFinished() {
                startActivity(Intent(this@SplashActivity.applicationContext, MainActivity::class.java))
                finish()
            }
        })
    }

}