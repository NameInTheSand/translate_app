package com.example.transapp.data.remoteDataSources.translate

import com.example.transapp.data.api.TranslatorApi
import com.example.transapp.data.models.TranslatedText
import com.example.transapp.data.utils.NetworkResult

interface TranslateRemoteDataSource {
    suspend fun translate(query: String): NetworkResult<TranslatedText, Exception>
}

class TranslateRemoteDataSourceImpl(
    private val remoteApi: TranslatorApi
) : TranslateRemoteDataSource {

    override suspend fun translate(query: String): NetworkResult<TranslatedText, Exception> {
        try {
            val response = remoteApi.sendTranslationData(query = query)
            return NetworkResult.Correct(response) as NetworkResult<TranslatedText, Exception>
        } catch (e: Exception) {
            return NetworkResult.Error(e)
                    as NetworkResult<TranslatedText, Exception>
        }
    }

}