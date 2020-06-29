package com.deserve.photosearch.home.domain

import com.deserve.photosearch.common.ResponseError
import com.deserve.photosearch.common.Result
import com.deserve.photosearch.home.model.PhotoSearchResponse

interface PhotoDataSource {
    fun searchPhotos(
        text: String,
        page: Int? = null,
        perPage: Int? = null
    ): Result<ResponseError, PhotoSearchResponse>
}
