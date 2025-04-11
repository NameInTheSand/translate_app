package com.example.transapp.data.repositories

import com.example.transapp.data.models.TranslatedText
import com.example.transapp.data.remoteDataSources.translate.TranslateRemoteDataSource
import com.example.transapp.data.utils.NetworkResult
import com.example.transapp.domain.repositories.translate.TranslateRepository

class TranslateRepositoryImpl(
    private val remoteDataSource: TranslateRemoteDataSource
) : TranslateRepository {
    override suspend fun translate(query: String): NetworkResult<TranslatedText, Exception> {
        return remoteDataSource.translate(query)
    }
}