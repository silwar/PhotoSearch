package com.silwar.photosearch.util

import com.silwar.photosearch.di.Injection

inline fun <reified T> String.parseResponse(): T {
    val gson = Injection.provideGson()
    return gson.fromJson(this, T::class.java)
}