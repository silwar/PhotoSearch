package com.silwar.photosearch.home.model

import com.silwar.photosearch.common.Constants

data class PhotoSearchRequest(
    private val searchText: String,
    private val baseUrl: String = Constants.BASE_URL,
    private val method: String = Constants.API_METHOD,
    private val apiKey: String = Constants.API_KEY,
    private val format: String = "json",
    private val noJsonCallback: Int = 1,
    private val safeSearch: Int = 1,
    private val page: Int?,
    private val perPage: Int?
) {
    fun get(): String {
        return "$baseUrl?method=$method" +
                "&api_key=$apiKey" +
                "&format=$format" +
                "&nojsoncallback=$noJsonCallback" +
                "&safe_search=$safeSearch" +
                getPage() +
                getPerPage() +
                "&text=$searchText"
    }

    private fun getPage(): String {
        return page?.let { "&page=$page" } ?: ""
    }

    private fun getPerPage(): String {
        return perPage?.let { "&per_page=$perPage" } ?: ""
    }
}