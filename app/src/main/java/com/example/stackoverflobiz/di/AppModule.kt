package com.example.stackoverflobiz.di

import com.example.datasource.models.ApiClient
import com.example.datasource.models.services.StackExchangeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient = ApiClient()

    @Provides
    @Singleton
    fun providesStackExchangeApi(apiClient: ApiClient): StackExchangeApi =
        apiClient.retrofit.create(StackExchangeApi::class.java)

}