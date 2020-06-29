package com.deserve.photosearch.di

import com.deserve.photosearch.home.domain.PhotoRemoteDataSource
import com.deserve.photosearch.home.domain.PhotoRepository
import com.deserve.photosearch.home.domain.SearchPhotoUseCase
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