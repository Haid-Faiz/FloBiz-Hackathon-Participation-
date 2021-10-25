package com.example.datasource.models

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val BASE_URL = "https://api.stackexchange.com/"

    private val okHttpBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                okHttpBuilder.build()
            ).build()
    }

//    fun <T> buildApi(api: Class<T>): T = retrofit.create(api)
}