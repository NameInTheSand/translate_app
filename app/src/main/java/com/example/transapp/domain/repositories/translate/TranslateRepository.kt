package com.example.transapp.domain.repositories.translate

import com.example.transapp.data.models.TranslatedText
import com.example.transapp.data.utils.NetworkResult

interface TranslateRepository {

    suspend fun translate(query: String): NetworkResult<TranslatedText, Exception>
}