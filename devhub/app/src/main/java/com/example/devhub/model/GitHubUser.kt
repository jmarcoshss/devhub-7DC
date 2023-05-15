package com.example.devhub.model

import com.squareup.moshi.Json

data class GitHubUser(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatar: String,
    val name: String,
    val bio: String
)