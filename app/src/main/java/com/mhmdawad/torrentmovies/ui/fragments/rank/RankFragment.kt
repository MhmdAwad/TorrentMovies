package com.mhmdawad.torrentmovies.ui.fragments.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.ui.fragments.home.HomeFragmentDirections
import com.mhmdawad.torrentmovies.utils.*
import kotlinx.android.synthetic.main.fragment_rank.*
import org.koin.android.viewmodel.ext.android.getViewModel

class RankFragment : Fragment(R.layout.fragment_rank), AdapterListener {

    private val rankAdapter by lazy { RankAdapter(this) }
    private lateinit var viewModel: RankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.getRankingMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearNoLimitFlag()
        initRecyclerView()
        observeObservers()
    }

    private fun observeObservers() {
        viewModel.observeRankMovies().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading-> rankSwipeRefresh.isRefreshing = true
                is Resource.Loaded-> {
                    rankAdapter.addList(it.data?.data?.movies!!)
                    rankSwipeRefresh.isRefreshing = false
                    rankNoInternet.gone()
                }
                is Resource.Error->{
                    rankSwipeRefresh.isRefreshing = false
                    rankNoInternet.show()
                }
            }
        })
    }

    private fun initRecyclerView() {
        rankRV.adapter = rankAdapter
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