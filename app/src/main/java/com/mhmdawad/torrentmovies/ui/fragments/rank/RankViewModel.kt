package com.mhmdawad.torrentmovies.ui.fragments.rank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.ui.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RankViewModel(private val repository: MainRepository): ViewModel() {

    private val rankMovies = MutableLiveData<Resource<MoviesResponse>>()
    fun observeRankMovies() = rankMovies as LiveData<Resource<MoviesResponse>>
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
                checkPageNum(page, results)
                cancelLoading = false
            }
            results.onFailure { rankMovies.postValue(Resource.Error("Error $it", null)) }
        }
    }

    private fun checkPageNum(page: Int, result: Result<MoviesResponse>) {
        if (page == 1)
            rankMovies.postValue(Resource.Loaded(result.getOrThrow()))
        else
            rankMovies.postValue(Resource.NewData(result.getOrThrow()))
    }

}