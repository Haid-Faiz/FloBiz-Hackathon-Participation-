package com.example.datasource.models.services

import com.example.datasource.models.QuestionsListResponse
import retrofit2.Response
import retrofit2.http.*

interface StackExchangeApi {

    @GET("2.2/questions")
    suspend fun fetchAllQuestions(
        @Query("key") apiKey: String = "ZiXCZbWaOwnDgpVT9Hx8IA((",
        @Query("order") orderBy: String = "desc",
        @Query("sort") sortBy: String = "activity",
        @Query("site") site: String = "stackoverflow",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
    ): Response<QuestionsListResponse>


    @GET("/2.3/search")
    suspend fun searchQuestions(
        @Query("key") apiKey: String = "ZiXCZbWaOwnDgpVT9Hx8IA((",
        @Query("intitle") searchQuery: String,
        @Query("order") orderBy: String = "desc",
        @Query("sort") sortBy: String = "votes",
        @Query("site") site: String = "stackoverflow",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
    ) : Response<QuestionsListResponse>

    @GET("/2.3/search")
    suspend fun filterQuestionsByTags(
        @Query("key") apiKey: String = "ZiXCZbWaOwnDgpVT9Hx8IA((",
        @Query("tagged") tags: String,   // ; seperated tags
        @Query("order") orderBy: String = "desc",
        @Query("sort") sortBy: String = "votes",
        @Query("site") site: String = "stackoverflow",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
    ) : Response<QuestionsListResponse>
}