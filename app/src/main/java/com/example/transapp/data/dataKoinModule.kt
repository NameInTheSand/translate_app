package com.example.transapp.data

import com.example.transapp.data.remoteDataSources.translate.TranslateRemoteDataSource
import com.example.transapp.data.remoteDataSources.translate.TranslateRemoteDataSourceImpl
import com.example.transapp.data.repositories.TranslateRepositoryImpl
import com.example.transapp.domain.repositories.translate.TranslateRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module

val dataModule = module {
    single<TranslateRemoteDataSource> { TranslateRemoteDataSourceImpl(get(), get()) }
    single<Gson> { Gson() }
    single<OkHttpClient> { OkHttpClient() }
    single<TranslateRepository> { TranslateRepositoryImpl(get()) }
}