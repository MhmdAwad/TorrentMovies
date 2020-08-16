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

    fun getRankingMovies(page: Int = 1){
        rankMovies.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val results = kotlin.runCatching { repository.getRankingMovies(page) }
            results.onSuccess { rankMovies.postValue(Resource.Loaded(results.getOrThrow())) }
            results.onFailure { rankMovies.postValue(Resource.Error("Error $it", null)) }
        }
    }

}