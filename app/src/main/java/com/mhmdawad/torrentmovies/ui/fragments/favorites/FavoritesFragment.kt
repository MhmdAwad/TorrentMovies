package com.mhmdawad.torrentmovies.ui.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.Resource
import com.mhmdawad.torrentmovies.utils.rv_listeners.FavoriteListener
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.viewmodel.ext.android.getViewModel


class FavoritesFragment : Fragment(R.layout.fragment_favorites), FavoriteListener {

    private val favAdapter by lazy { FavoritesAdapter(this) }
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeObservers()
    }

    private fun observeObservers() {
        viewModel.observeFavMovies().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{ println("LOADING")}
                is Resource.Loaded -> favAdapter.addFavList(it.data!!)
                is Resource.Error ->{ println("ERROR")}
            }
        })
    }

    private fun initRecyclerView() {
        favoriteRV.apply {
            adapter = favAdapter
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.05f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER)
                    .setPivotY(Pivot.Y.BOTTOM)
                    .build()
            )
        }
    }

    override fun onDeleteFavMovie(id: Int) {
        viewModel.deleteSpecificMovie(id)
    }

}