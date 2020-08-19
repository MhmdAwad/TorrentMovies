package com.mhmdawad.torrentmovies.ui.fragments.favorites

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.*
import com.mhmdawad.torrentmovies.utils.rv_listeners.FavoriteListener
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.viewmodel.ext.android.getViewModel


class FavoritesFragment : Fragment(R.layout.fragment_favorites), FavoriteListener, DiscreteScrollView.OnItemChangedListener<FavoritesAdapter.FavoriteViewHolder> {

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
                is Resource.Loading ->{
                    favMovieProgress.show()
                    noMoviesContainer.gone()
                    favoriteRV.gone()
                    favMovieBackground.gone()
                }
                is Resource.Loaded -> {
                    favMovieProgress.gone()
                    noMoviesContainer.gone()
                    favoriteRV.show()
                    favMovieBackground.show()
                    favAdapter.addFavList(it.data!!)
                }
                is Resource.Error ->{
                    favMovieProgress.gone()
                    noMoviesContainer.show()
                    favoriteRV.gone()
                    favMovieBackground.gone()
                }
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
            addOnItemChangedListener(this@FavoritesFragment)
        }
    }

    override fun onDeleteFavMovie(id: Int) {
        viewModel.deleteSpecificMovie(id)
    }

    override fun onMovieClicked(id: Int, imageView: ImageView) {
        val extras =
            FragmentNavigatorExtras(imageView to resources.getString(R.string.transitionName))
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(id)
        findNavController().navigate(action, extras)
        activity?.hideBottomNav()
    }

    override fun onResume() {
        super.onResume()
        activity?.showBottomNav()
    }

    override fun onCurrentItemChanged(
        viewHolder: FavoritesAdapter.FavoriteViewHolder?,
        adapterPosition: Int
    ) {
        favMovieBackground.downloadImage(favAdapter.getMovieCover(adapterPosition))

    }
}