package com.silwar.photosearch.common

import com.google.gson.annotations.SerializedName


open class BaseResponse(
    @SerializedName("stat")
    val stat: String = "unknown",

    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("message")
    val message: String? = null
)