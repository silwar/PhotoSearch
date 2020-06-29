package com.silwar.photosearch.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.silwar.photosearch.common.*
import com.silwar.photosearch.di.Injection
import com.silwar.photosearch.home.model.Photo
import com.silwar.photosearch.home.model.PhotoSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotoSearchViewModel : BaseViewModel() {
    private val searchPhotoUseCase = Injection.provideSearchPhotoUseCase()

    private val photosList = MutableLiveData<Event<List<Photo>>>()

    private val loadingState = MutableLiveData<Event<Boolean>>()

    private val refreshingState = MutableLiveData<Event<Boolean>>()

    private val loadMoreState = MutableLiveData<Event<Boolean>>()

    private var searchText: String = ""

    private var currentPage: Int = Constants.DEFAULT_PAGE

    private var totalPages: Int = 1

    fun getPhotoList(): LiveData<Event<List<Photo>>> {
        return photosList
    }

    fun isLoading(): LiveData<Event<Boolean>> {
        return loadingState
    }

    fun isRefreshing(): LiveData<Event<Boolean>> {
        return refreshingState
    }

    fun isLoadingMorePhotos(): LiveData<Event<Boolean>> {
        return loadMoreState
    }

    fun searchPhoto(text: String) {
        reset()
        loadingState.postValue(Event(true))
        searchText = text
        performSearch(text, currentPage)
    }

    fun refreshPhotos() {
        reset()
        refreshingState.postValue(Event(true))
        currentPage = Constants.DEFAULT_PAGE
        performSearch(searchText, currentPage)
    }

    fun loadMorePhotos() {
        if (currentPage < totalPages) {
            currentPage += 1
            loadMoreState.postValue(Event(true))
            performSearch(searchText, currentPage)
        }
    }

    fun reset() {
        currentPage = Constants.DEFAULT_PAGE
        totalPages = Constants.DEFAULT_PAGE
    }

    private fun performSearch(text: String, page: Int) {
        val job = Job()
        viewModelScope.launch(Dispatchers.IO + job) {
            val result = searchPhotoUseCase.searchPhotos(text, page)
            result.let { searchResult ->
                stopLoading()
                processSearchResult(searchResult)
            }
        }
    }

    private fun processSearchResult(result: Result<ResponseError, PhotoSearchResponse>) {
        when (result) {
            is Result.Success -> {
                totalPages = result.data.photos?.pages ?: Constants.DEFAULT_PAGE
                val list = result.data.photos?.photo ?: listOf()
                photosList.postValue(Event(list))
            }
            is Result.Error -> errorLiveData.postValue(
                Event(
                    CommonError(
                        result.error.code,
                        result.error.message
                    )
                )
            )
        }
    }


    private fun stopLoading() {
        if (refreshingState.value?.peekContent() == true) {
            refreshingState.postValue(Event(false))
        }
        if (loadingState.value?.peekContent() == true) {
            loadingState.postValue(Event(false))
        }
        if (loadMoreState.value?.peekContent() == true) {
            loadMoreState.postValue(Event(false))
        }
    }
}