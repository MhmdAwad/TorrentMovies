package com.mhmdawad.torrentmovies.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.data.source.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MainRepository): ViewModel() {

    private var movieDetails = MutableLiveData<Resource<Movie>>()
    fun getMovieDetails() = movieDetails as LiveData<Resource<Movie>>

    fun getMovieDetails(id: Int) {
        movieDetails.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = kotlin.runCatching { repository.getCacheDetails(id) }
            result.onSuccess {
                movieDetails.postValue(Resource.Loaded(it))
            }
            result.onFailure {
                movieDetails.postValue(Resource.Error("Error: $it", null))
            }
        }
    }
}