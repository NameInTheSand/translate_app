package com.example.transapp.data.api

import com.example.transapp.data.models.TranslatedBody
import com.example.transapp.data.models.TranslatedText
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslatorApi {
    @POST("external-api/free-google-translator")
    suspend fun sendTranslationData(
        @Query("from") from: String = "en",
        @Query("to") to: String = "uk",
        @Query("query") query: String,
        @Body body: TranslatedBody = TranslatedBody()
    ) : TranslatedText
}