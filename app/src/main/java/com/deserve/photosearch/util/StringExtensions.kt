package com.deserve.photosearch.util

import com.deserve.photosearch.di.Injection

inline fun <reified T> String.parseResponse(): T {
    val gson = Injection.provideGson()
    return gson.fromJson(this, T::class.java)
}