package com.example.transapp.data.api

import com.example.transapp.data.models.TranslatedBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstanceHelper {

    fun createRetrofit(apiKey: String): TranslatorApi {
        val retrofit = Retrofit.Builder().baseUrl(
            "https://free-google-translator.p.rapidapi.com/"
        ).client(getOkHttpClient(apiKey)).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(TranslatorApi::class.java)
    }

    private fun getOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(
            5000, TimeUnit.MILLISECONDS
        ).addInterceptor(
            HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }
        ).addInterceptor(
            TokenAuthInterceptor(apiKey)
        ).build()
    }

}

private class TokenAuthInterceptor(val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val requestBuilder: Request.Builder = original.newBuilder()
            .header("x-rapidapi-key", apiKey)
            .header("x-rapidapi-host", "free-google-translator.p.rapidapi.com")

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}