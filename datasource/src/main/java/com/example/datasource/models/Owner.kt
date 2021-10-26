package com.example.datasource.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "accept_rate")
    var acceptRate: Int?,
    @Json(name = "display_name")
    var displayName: String,
    @Json(name = "link")
    var link: String,
    @Json(name = "profile_image")
    var profileImage: String,
    @Json(name = "reputation")
    var reputation: Int,
    @Json(name = "user_id")
    var userId: Int,
    @Json(name = "user_type")
    var userType: String
)