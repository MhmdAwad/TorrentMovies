package com.mhmdawad.torrentmovies.ui

import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.ui.fragments.details.DetailsFragment
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        with(navHostFragment!!.childFragmentManager.fragments[0]) {
            if (this is DetailsFragment)
                this.onBackPressed()
            else
                super.onBackPressed()
        }

    }
}