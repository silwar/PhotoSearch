package com.deserve.photosearch.common

sealed class Result<out T, out E> {
    data class Success<out T>(val data: T) : Result<Nothing, T>()
    data class Error<out E>(val error: E) : Result<E, Nothing>()
}