package com.silwar.photosearch.home.domain

import com.silwar.photosearch.common.ResponseError
import com.silwar.photosearch.common.Result
import com.silwar.photosearch.home.model.PhotoSearchRequest
import com.silwar.photosearch.home.model.PhotoSearchResponse
import com.silwar.photosearch.util.getSSLSocketFactory
import com.silwar.photosearch.util.parseResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class PhotoRemoteDataSource : PhotoDataSource {
    override fun searchPhotos(
        text: String,
        page: Int?,
        perPage: Int?
    ): Result<ResponseError, PhotoSearchResponse> {
        val response = getPhotos(getUrl(text, page, perPage))
        val parsedResponse = response?.parseResponse<PhotoSearchResponse>()
        return parsedResponse?.let {
            if (it.stat == "fail") {
                Result.Error(
                    ResponseError(
                        it.stat,
                        it.code ?: 0,
                        it.message ?: ""
                    )
                )
            } else {
                return Result.Success(parsedResponse)
            }
        } ?: Result.Error(ResponseError("fail", 100, ""))
    }

    private fun getPhotos(url: URL): String? {
        var urlConnection: HttpsURLConnection? = null
        return try {
            urlConnection =
                url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET";
            //TODO("Make this secure")
            urlConnection.sslSocketFactory = getSSLSocketFactory()
            val inputStream: InputStream = urlConnection.inputStream
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            bufferedReader.readLine()
        } catch (e: IOException) {
            null
        } finally {
            urlConnection?.disconnect()
        }

    }

    private fun getUrl(text: String, page: Int?, perPage: Int?): URL {
        val request = PhotoSearchRequest(searchText = text, page = page, perPage = perPage)
        return URL(request.get())
    }

}