package com.mhmdawad.torrentmovies.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.MoviesResponse
import com.mhmdawad.torrentmovies.ui.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    private var moviesData = MutableLiveData<Resource<MoviesResponse>>()
    fun getMovies() = moviesData as LiveData<Resource<MoviesResponse>>
    private lateinit var lastDataToLoad: Pair<Boolean, String>
    private var pageNumber = 1

    init {
        moviesCategoryList()
    }

    fun refreshData(){
        pageNumber = 0
        loadMoreData()
    }
    fun loadMoreData() {
        if (lastDataToLoad.first)
            moviesCategoryList(lastDataToLoad.second, ++pageNumber)
        else
            searchMovie(lastDataToLoad.second, ++pageNumber)
    }

    fun moviesCategoryList(category: String = "", page: Int = 1) {
        lastDataToLoad = Pair(true, category)
        moviesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { repository.getMoviesCategory(category, page) }
            result.onSuccess { checkPageNum(page, result) }
            result.onFailure {
                moviesData.postValue(Resource.Error("Error with loading data", null))
            }
        }
    }

    private fun checkPageNum(page: Int, result: Result<MoviesResponse>) {
        if (page == 1)
            moviesData.postValue(Resource.Loaded(result.getOrThrow()))
        else
            moviesData.postValue(Resource.NewData(result.getOrThrow()))
    }

    fun searchMovie(search: String, page: Int = 1) {
        pageNumber = page
        lastDataToLoad = Pair(false, search)
        moviesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { repository.getMovieSearch(search, page) }
            result.onSuccess { checkPageNum(page, result) }
            result.onFailure {
                moviesData.postValue(Resource.Error("Error with loading data", null))
            }
        }
    }


}