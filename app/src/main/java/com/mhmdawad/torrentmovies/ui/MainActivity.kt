package com.mhmdawad.torrentmovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.ui.fragments.details.DetailsFragment
import com.mhmdawad.torrentmovies.ui.fragments.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(mainBottomNav, navController)
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        with(navHostFragment!!.childFragmentManager.fragments[0]) {
            when (this){
                is HomeFragment  -> checkCloseApp(this.onBackPressed())
                is DetailsFragment -> checkCloseApp(this.onBackPressed())
                else -> super.onBackPressed()
            }
        }
    }

    private fun checkCloseApp(close: Boolean){
        if(close)
            finish()
    }
}