package com.example.datasource.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionsListResponse(
    @Json(name = "has_more")
    var hasMore: Boolean,
    @Json(name = "items")
    var questions: List<Question>,
    @Json(name = "quota_max")
    var quotaMax: Int,
    @Json(name = "quota_remaining")
    var quotaRemaining: Int
)