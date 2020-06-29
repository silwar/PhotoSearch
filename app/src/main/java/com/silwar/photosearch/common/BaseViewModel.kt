package com.silwar.photosearch.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val networkAvailability = MutableLiveData<Event<Boolean>>()
    protected val errorLiveData = MutableLiveData<Event<CommonError>>()
    protected val warningLiveData = MutableLiveData<Event<String>>()

    fun getNetworkAvailability() : LiveData<Event<Boolean>>{
        return networkAvailability
    }

    fun getError() : LiveData<Event<CommonError>>{
        return errorLiveData
    }

    fun getWarning() : LiveData<Event<String>>{
        return warningLiveData
    }
}