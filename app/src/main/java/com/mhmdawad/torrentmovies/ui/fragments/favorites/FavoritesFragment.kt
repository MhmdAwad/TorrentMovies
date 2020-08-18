package com.mhmdawad.torrentmovies.ui.fragments.favorites

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import com.mhmdawad.torrentmovies.R
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val favAdapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dummmyData()

        favoriteRV.apply {
            adapter = favAdapter
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.05f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER)
                    .setPivotY(Pivot.Y.CENTER)
                    .build()
            )
        }
    }

    private fun dummmyData() {
        val list = mutableListOf<String>()
        list.add("https://yts.mx//assets//images//movies//joker_2019//large-cover.jpg")
        list.add("https:\\/\\/yts.mx\\/assets\\/images\\/movies\\/demons_inside_me_2019\\/medium-cover.jpg")
        list.add("https:\\/\\/yts.mx\\/assets\\/images\\/movies\\/house_of_darkness_new_blood_2018\\/medium-cover.jpg")
        list.add("https:\\/\\/yts.mx\\/assets\\/images\\/movies\\/escape_puzzle_of_fear_2020\\/medium-cover.jpg")
        list.add("https:\\/\\/yts.mx\\/assets\\/images\\/movies\\/blue_thunder_1983\\/medium-cover.jpg")
        list.add("https:\\/\\/yts.mx\\/assets\\/images\\/movies\\/hiroshima_1953\\/medium-cover.jpg")
        favAdapter.addFavList(list)

    }
}