package com.deserve.photosearch.home.domain

import com.deserve.photosearch.common.ResponseError
import com.deserve.photosearch.common.Result
import com.deserve.photosearch.home.model.PhotoSearchResponse

class PhotoRepository(private val remoteDataSource: PhotoRemoteDataSource) : PhotoDataSource {

    override fun searchPhotos(
        text: String,
        page: Int?,
        perPage: Int?
    ): Result<ResponseError, PhotoSearchResponse> {
        return remoteDataSource.searchPhotos(text, page, perPage)
    }
}