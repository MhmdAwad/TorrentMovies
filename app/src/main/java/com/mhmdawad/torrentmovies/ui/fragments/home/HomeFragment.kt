package com.mhmdawad.torrentmovies.ui.fragments.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
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
import com.mhmdawad.torrentmovies.utils.AdapterListener
import com.mhmdawad.torrentmovies.utils.Constants
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.getViewModel


class HomeFragment : Fragment(R.layout.fragment_home), AdapterListener {

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
        changeStatusBar()
        initRecyclerViews()
        viewsListener(view.context)
        observeObservers()
    }

    private fun observeObservers() {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer { dataObserve->
            refreshMoviesList.isRefreshing = false

            when (dataObserve) {
                is Resource.Error -> Log.d("TAG", "Error ${dataObserve.msg}")
                is Resource.Loading -> Log.d("TAG", "Loading")
                is Resource.Loaded -> {
                    with(dataObserve.data?.data?.movies){
                        if (this!!.size < 20)
                            exploreAdapter.stopLoading()
                        exploreRV.scrollToPosition(0)
                        exploreAdapter.addList(this)
                        animationRV()
                    }
                }
                is Resource.NewData -> {
                    with(dataObserve.data?.data?.movies){
                        if (this!!.isEmpty())
                            exploreAdapter.stopLoading()
                        else
                            exploreAdapter.updateList(this)
                    }
                }
            }

        })
    }

    private fun viewsListener(context: Context){
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
        val controller: GridLayoutAnimationController = getKoin().get()
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
                    if (!recyclerView.canScrollVertically(1) && exploreAdapter.loadMore())
                        viewModel.loadMoreData()
                }
            })
        }
        categoryRV.apply {
            adapter = categoryAdapter
        }
    }


    private fun changeStatusBar() {
        activity?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun itemClicked(pos: Int) {
        exploreAdapter.keepLoading()
        viewModel.moviesCategoryList(categoryList[pos].second)
    }

    override fun openMovie(movieID: Int, movieBackground: String, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(imageView to resources.getString(R.string.transitionName))
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                movieID
            )
        findNavController().navigate(action, extras)
    }

}