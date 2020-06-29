package com.silwar.photosearch.home.domain

import com.silwar.photosearch.common.ResponseError
import com.silwar.photosearch.common.Result
import com.silwar.photosearch.common.UseCase
import com.silwar.photosearch.home.model.PhotoSearchResponse

class SearchPhotoUseCase(private val repository: PhotoRepository) : UseCase {
    override fun execute() {
        TODO("Not yet implemented")
    }

    fun searchPhotos(
        text: String,
        page: Int? = null,
        perPage: Int? = null
    ): Result<ResponseError, PhotoSearchResponse> {
        return repository.searchPhotos(text, page, perPage)
    }
}