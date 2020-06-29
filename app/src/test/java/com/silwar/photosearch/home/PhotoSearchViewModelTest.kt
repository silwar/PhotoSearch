package com.silwar.photosearch.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.silwar.photosearch.common.Event
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoSearchViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : PhotoSearchViewModel

    @Mock
    private lateinit var stateObserver: Observer<Event<Boolean>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(PhotoSearchViewModel())
    }

    @Test
    fun when_SearchPhotoCall_isLoadingState_ShouldBeTrue() {
        viewModel.searchPhoto("ABC")
        viewModel.isLoading().observeForever(stateObserver)

        verify(stateObserver).onChanged(
            Event(true)
        )
        viewModel.isLoading().removeObserver(stateObserver)
    }

    @Test
    fun when_OnRefreshCall_isRefreshingState_ShouldBeTrue() {
        viewModel.refreshPhotos()
        viewModel.isRefreshing().observeForever(stateObserver)

        verify(stateObserver).onChanged(
            Event(true)
        )
        viewModel.isRefreshing().removeObserver(stateObserver)
    }
}