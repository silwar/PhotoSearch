package com.deserve.photosearch.home.domain

import com.deserve.photosearch.common.ResponseError
import com.deserve.photosearch.common.Result
import com.deserve.photosearch.common.UseCase
import com.deserve.photosearch.home.model.PhotoSearchResponse

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