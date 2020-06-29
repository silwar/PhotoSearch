package com.silwar.photosearch.home.domain

import com.silwar.photosearch.common.ResponseError
import com.silwar.photosearch.common.Result
import com.silwar.photosearch.home.model.PhotoSearchResponse

class PhotoRepository(private val remoteDataSource: PhotoRemoteDataSource) : PhotoDataSource {

    override fun searchPhotos(
        text: String,
        page: Int?,
        perPage: Int?
    ): Result<ResponseError, PhotoSearchResponse> {
        return remoteDataSource.searchPhotos(text, page, perPage)
    }
}