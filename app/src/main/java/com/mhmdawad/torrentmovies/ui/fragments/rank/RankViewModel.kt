package com.mhmdawad.torrentmovies.ui.fragments.rank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.data.source.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RankViewModel(private val repository: MainRepository): ViewModel() {

    private val rankMovies = MutableLiveData<Resource<List<MoviesItem>>>()
    fun observeRankMovies() = rankMovies as LiveData<Resource<List<MoviesItem>>>
    private var cancelLoading = false
    private var pageNum = 1

    init {
        getRankingMovies()
    }

    fun refreshData() {
        pageNum = 0
        cancelLoading = false
        loadMoreData()
    }

    fun loadMoreData(){
        if(!cancelLoading)
            getRankingMovies(++pageNum)
    }

    private fun getRankingMovies(page: Int = 1){
        cancelLoading = true
        rankMovies.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val results = kotlin.runCatching { repository.getNetworkRanking(page) }
            results.onSuccess {
                checkPageNum(page, it)
                cancelLoading = false
            }
            results.onFailure { rankMovies.postValue(Resource.Error("Error $it", null)) }
        }
    }

    private fun checkPageNum(page: Int, result: List<MoviesItem>) {
        if (page == 1)
            rankMovies.postValue(Resource.Loaded(result))
        else
            rankMovies.postValue(Resource.NewData(result))
    }

}