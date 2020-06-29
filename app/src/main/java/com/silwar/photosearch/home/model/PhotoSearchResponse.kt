package com.silwar.photosearch.home.model

import com.silwar.photosearch.common.BaseResponse
import com.google.gson.annotations.SerializedName


data class PhotoSearchResponse(

	@SerializedName("photos")
	val photos: Photos? = null

) : BaseResponse()

data class Photos(

	@SerializedName("perpage")
	val perPage: Int = 100,

	@SerializedName("total")
	val total: String? = null,

	@SerializedName("pages")
	val pages: Int = 1,

	@SerializedName("photo")
	val photo: List<Photo>? = null,

	@SerializedName("page")
	val page: Int = 1
)

data class Photo(

	@SerializedName("owner")
	val owner: String? = null,

	@SerializedName("server")
	val server: String? = null,

	@SerializedName("ispublic")
	val isPublic: Int? = null,

	@SerializedName("isfriend")
	val isFriend: Int? = null,

	@SerializedName("farm")
	val farm: Int? = null,

	@SerializedName("id")
	val id: String? = null,

	@SerializedName("secret")
	val secret: String? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("isfamily")
	val isFamily: Int? = null
)
