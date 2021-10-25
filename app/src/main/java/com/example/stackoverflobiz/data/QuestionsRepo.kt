package com.example.stackoverflobiz.data

import com.example.datasource.models.QuestionsListResponse
import com.example.datasource.models.services.StackExchangeApi
import com.example.stackoverflobiz.utils.Resource

class QuestionsRepo(
private val api: StackExchangeApi
) : BaseRepo() {

    suspend fun fetchAllQuestions() = safeApiCall {
        api.fetchAllQuestions()
    }

    fun fetchSearchedQuestions(searchQuery: String) = safeApiCall {
        api.searchQuestions(searchQuery)
    }


}
