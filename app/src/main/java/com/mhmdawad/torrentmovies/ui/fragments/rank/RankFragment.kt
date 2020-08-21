package com.mhmdawad.torrentmovies.ui.fragments.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.*
import com.mhmdawad.torrentmovies.utils.rv_listeners.AdapterListener
import kotlinx.android.synthetic.main.fragment_rank.*
import org.koin.android.viewmodel.ext.android.getViewModel

class RankFragment : Fragment(R.layout.fragment_rank),
    AdapterListener {

    private val rankAdapter by lazy { RankAdapter(this) }
    private lateinit var viewModel: RankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeObservers()
        initRecyclerView()
        viewsListener()
    }

    private fun viewsListener() {
        rankSwipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    private fun observeObservers() {
        viewModel.observeRankMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> rankSwipeRefresh.isRefreshing = true
                is Resource.Loaded -> {
                    rankAdapter.addList(it.data!!)
                    rankSwipeRefresh.isRefreshing = false
                    rankNoInternet.gone()
                    rankRV.show()
                }
                is Resource.Error -> {
                    println("========== ${it.msg}")
                    rankSwipeRefresh.isRefreshing = false
                    if(rankAdapter.itemCount < 10){
                        rankNoInternet.show()
                        rankRV.gone()
                    }
                }
                is Resource.NewData -> {
                    rankSwipeRefresh.isRefreshing = false
                    rankAdapter.updateList(it.data!!)
                }
            }
        })
    }

    private fun initRecyclerView() {
        rankRV.apply {
            adapter = rankAdapter
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
    }

    override fun openMovie(movieID: Int, imageView: ImageView) {
        val extras =
            FragmentNavigatorExtras(imageView to resources.getString(R.string.transitionName))
        val action =
            RankFragmentDirections.actionRankFragmentToDetailsFragment(
                movieID
            )
        findNavController().navigate(action, extras)
        activity?.hideBottomNav()
    }

    override fun onResume() {
        super.onResume()
        activity?.showBottomNav()
    }

    override fun itemClicked(pos: Int) {
        TODO("Not yet implemented")
    }
}