package com.silwar.photosearch.common

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("stats")
    val stats: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)