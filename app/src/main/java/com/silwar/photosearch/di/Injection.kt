package com.silwar.photosearch.di

import com.silwar.photosearch.home.domain.PhotoRemoteDataSource
import com.silwar.photosearch.home.domain.PhotoRepository
import com.silwar.photosearch.home.domain.SearchPhotoUseCase
import com.google.gson.Gson

object Injection {

    @JvmStatic
    internal fun providePhotoRepository(): PhotoRepository {
        return PhotoRepository(PhotoRemoteDataSource())
    }

    @JvmStatic
    fun provideSearchPhotoUseCase(): SearchPhotoUseCase{
        return SearchPhotoUseCase(providePhotoRepository())
    }

    @JvmStatic
    fun provideGson(): Gson{
        return Gson()
    }
}