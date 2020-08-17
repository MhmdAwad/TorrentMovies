package com.mhmdawad.torrentmovies.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.ui.MainRepository
import com.mhmdawad.torrentmovies.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    private var moviesData = MutableLiveData<Resource<List<MoviesItem>>>()
    fun getMovies() = moviesData as LiveData<Resource<List<MoviesItem>>>
    private lateinit var lastDataToLoad: Pair<Boolean, String>
    private var pageNumber = 1
    private var cancel = false

    init {
        moviesCategoryList()
    }

    fun refreshData() {
        pageNumber = 0
        cancel = false
        loadMoreData()
    }

    fun loadMoreData() {
        if (!cancel)
            if (lastDataToLoad.first)
                moviesCategoryList(lastDataToLoad.second, ++pageNumber)
            else
                searchMovie(lastDataToLoad.second, ++pageNumber)
    }

    fun moviesCategoryList(category: String = "", page: Int = 1) {
        lastDataToLoad = Pair(true, category)
        cancel = true
        moviesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { repository.getCacheCategory(category, page) }
            result.onSuccess {
                    checkPageNum(page, result.getOrThrow())
            }
            result.onFailure {
                moviesData.postValue(Resource.Error("Error with loading data", null))
            }
        }
    }

    private fun checkPageNum(page: Int, result: List<MoviesItem>) {
        cancel = false
        if (page == 1)
            moviesData.postValue(Resource.Loaded(result))
        else
            moviesData.postValue(Resource.NewData(result))
    }

    fun searchMovie(search: String, page: Int = 1) {
        cancel = true
        pageNumber = page
        lastDataToLoad = Pair(false, search)
        moviesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { repository.getNetworkSearch(search, page) }
            result.onSuccess {
                checkPageNum(page, result.getOrThrow().data?.movies!!)

            }
            result.onFailure {
                moviesData.postValue(Resource.Error("Error with loading data", null))
            }
        }
    }


}