package com.example.transapp.data.remoteDataSources.translate

import com.example.transapp.data.models.TranslatedText
import com.example.transapp.data.utils.NetworkResult
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

interface TranslateRemoteDataSource {
    suspend fun translate(query: String): NetworkResult<TranslatedText, Exception>
}

class TranslateRemoteDataSourceImpl(
    private val client: OkHttpClient,
    private val gson: Gson
) : TranslateRemoteDataSource {

    override suspend fun translate(query: String): NetworkResult<TranslatedText, Exception> {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = """{"translate":"rapidapi"}""".toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://free-google-translator.p.rapidapi.com/external-api/free-google-translator?from=en&to=uk&query=$query")
            .post(body)
            .addHeader("x-rapidapi-key", "28a55eebd3mshf6002e65bede104p1ad9aejsnf6f6cc84417b")
            .addHeader("x-rapidapi-host", "free-google-translator.p.rapidapi.com")
            .addHeader("Content-Type", "application/json")
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            val translatedText = gson.fromJson(json, TranslatedText::class.java)
            return NetworkResult.Correct(translatedText) as NetworkResult<TranslatedText, Exception>
        } catch (e: Exception) {
            return NetworkResult.Error(e)
                    as NetworkResult<TranslatedText, Exception>
        }
    }

}