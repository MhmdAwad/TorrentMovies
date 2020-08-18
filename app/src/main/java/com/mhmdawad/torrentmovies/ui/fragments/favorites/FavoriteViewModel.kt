package com.mhmdawad.torrentmovies.ui.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.FavoriteMovie
import com.mhmdawad.torrentmovies.data.source.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MainRepository) : ViewModel() {

    private val favMovies = MutableLiveData<Resource<List<FavoriteMovie>>>()
    fun observeFavMovies() = favMovies as LiveData<Resource<List<FavoriteMovie>>>

    init {
        getAllFavoriteMovies()
    }

    private fun getAllFavoriteMovies() {
        favMovies.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = kotlin.runCatching { repository.getAllFavMovie() }
            result.onSuccess { favMovies.postValue(Resource.Loaded(result.getOrThrow())) }
            result.onFailure {
                favMovies.postValue(Resource.Error("Empty List"))
            }
        }
    }


}