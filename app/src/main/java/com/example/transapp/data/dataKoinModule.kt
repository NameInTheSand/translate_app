package com.example.transapp.data

import androidx.compose.ui.input.key.Key
import com.example.transapp.data.api.RetrofitInstanceHelper
import com.example.transapp.data.api.TranslatorApi
import com.example.transapp.data.remoteDataSources.translate.TranslateRemoteDataSource
import com.example.transapp.data.remoteDataSources.translate.TranslateRemoteDataSourceImpl
import com.example.transapp.data.repositories.TranslateRepositoryImpl
import com.example.transapp.domain.repositories.translate.TranslateRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module

fun getDataModule(apiKey: String) = module {
    single<TranslateRemoteDataSource> { TranslateRemoteDataSourceImpl(get()) }
    single<TranslateRepository> { TranslateRepositoryImpl(get()) }
    single<TranslatorApi> { RetrofitInstanceHelper.createRetrofit(apiKey) }
}