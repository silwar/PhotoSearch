package com.silwar.photosearch.home.domain

import com.silwar.photosearch.common.ResponseError
import com.silwar.photosearch.common.Result
import com.silwar.photosearch.home.model.PhotoSearchResponse

interface PhotoDataSource {
    fun searchPhotos(
        text: String,
        page: Int? = null,
        perPage: Int? = null
    ): Result<ResponseError, PhotoSearchResponse>
}
