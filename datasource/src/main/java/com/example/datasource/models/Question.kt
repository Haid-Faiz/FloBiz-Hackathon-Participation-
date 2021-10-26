package com.example.datasource.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "accepted_answer_id")
    var acceptedAnswerId: Int?,
    @Json(name = "answer_count")
    var answerCount: Int,
    @Json(name = "closed_date")
    var closedDate: Int?,
    @Json(name = "closed_reason")
    var closedReason: String?,
    @Json(name = "content_license")
    var contentLicense: String?,
    @Json(name = "creation_date")
    var creationDate: Int,
    @Json(name = "is_answered")
    var isAnswered: Boolean,
    @Json(name = "last_activity_date")
    var lastActivityDate: Int,
    @Json(name = "last_edit_date")
    var lastEditDate: Int?,
    @Json(name = "link")
    var link: String?,
    @Json(name = "owner")
    var owner: Owner,
    @Json(name = "question_id")
    var questionId: Int,
    @Json(name = "score")
    var score: Int,
    @Json(name = "tags")
    var tags: List<String>,
    @Json(name = "title")
    var title: String,
    @Json(name = "view_count")
    var viewCount: Int
)