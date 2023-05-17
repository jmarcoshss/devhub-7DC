package com.example.devhub.model

import com.example.devhub.ui.screen.ProfileUiState
import com.squareup.moshi.Json

data class GitHubUser(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatar: String,
    val name: String,
    val bio: String
)
fun GitHubUser.toProfileUiState():ProfileUiState{
    return ProfileUiState(
        user = login,
        image = avatar,
        name = name,
        bio = bio
    )
}