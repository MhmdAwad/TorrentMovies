package com.mhmdawad.torrentmovies.ui.fragments.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.GridLayoutAnimationController
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.*
import com.mhmdawad.torrentmovies.utils.rv_listeners.AdapterListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent
import org.koin.core.get


class HomeFragment : Fragment(R.layout.fragment_home),
    AdapterListener, IOnBackPressed, KoinComponent {

    private lateinit var viewModel: HomeViewModel
    private val exploreAdapter by lazy {
        ExploreAdapter(
            this
        )
    }
    private val categoryList by lazy { Constants.getMoviesGenre() }
    private val categoryAdapter by lazy {
        CategoryAdapter(
            categoryList,
            this
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel()
        clearNoLimitFlag()
        initRecyclerViews()
        viewsListener(view.context)
        observeObservers()
    }

    private fun observeObservers() {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer { dataObserve ->
            when (dataObserve) {
                is Resource.Error -> {
                    refreshMoviesList.isRefreshing = false
                    if (exploreAdapter.itemCount < 10) {
                        homeInternetConnection.show()
                        exploreRV.gone()
                    }
                }
                is Resource.Loading -> refreshMoviesList.isRefreshing = true
                is Resource.Loaded -> {
                    refreshMoviesList.isRefreshing = false
                    homeInternetConnection.gone()
                    exploreRV.show()
                    with(dataObserve.data) {
                        exploreRV.smoothScrollToPosition(0)
                        exploreAdapter.addList(this!!)
                        animationRV()
                    }
                }
                is Resource.NewData -> {
                    refreshMoviesList.isRefreshing = false
                    with(dataObserve.data) {
                        exploreAdapter.updateList(this!!)
                    }
                }
            }
        })
    }

    private fun viewsListener(context: Context) {
        textView.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_detailsFragment)
        }

        searchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && searchMovie.text.isNotEmpty()) {
                viewModel.searchMovie(searchMovie.text.toString())
                closeSearch(context)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        refreshMoviesList.setOnRefreshListener { viewModel.refreshData() }
    }

    private fun closeSearch(context: Context) {
        searchMovie.text.clear()
        searchMovie.clearFocus()
        resetCategoryRV()
        val input = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun resetCategoryRV() {
        if (categoryAdapter.selectedIndex > 0) {
            categoryRV.smoothScrollToPosition(0)
            categoryAdapter.reset()
        }
    }

    private fun animationRV() {
        val controller: GridLayoutAnimationController = get()
        exploreRV.layoutAnimation = controller
        exploreRV.startLayoutAnimation()
    }

    private fun initRecyclerViews() {
        exploreRV.apply {
            adapter = exploreAdapter
            itemAnimator = null
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1))
                        viewModel.loadMoreData()
                }
            })
        }
        categoryRV.apply {
            adapter = categoryAdapter
        }
    }


    override fun itemClicked(pos: Int) {
        viewModel.moviesCategoryList(categoryList[pos].second)
        exploreAdapter.clearList()
    }

    override fun openMovie(movieID: Int, imageView: ImageView) {
        val extras =
            FragmentNavigatorExtras(imageView to resources.getString(R.string.transitionName))
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                movieID
            )
        findNavController().navigate(action, extras)
        activity?.hideBottomNav()
    }

    override fun onResume() {
        super.onResume()
        activity?.showBottomNav()
    }

    override fun onBackPressed(): Boolean {
        if (exploreAdapter.currentPosition > 10) {
            viewModel.refreshData()
            return false
        }
        return true
    }

}