package com.example.stackoverflobiz.data

import com.example.datasource.models.services.StackExchangeApi

class QuestionsRepo(
private val api: StackExchangeApi
) : BaseRepo() {

    suspend fun fetchAllQuestions() = safeApiCall {
        api.fetchAllQuestions()
    }

    suspend fun getSearchedQuestions(searchQuery: String) = safeApiCall {
        api.searchQuestions(searchQuery = searchQuery)
    }

    suspend fun getFilteredQuestionsByTags(tags: String) = safeApiCall {
        api.filterQuestionsByTags(tags = tags)
    }

}
