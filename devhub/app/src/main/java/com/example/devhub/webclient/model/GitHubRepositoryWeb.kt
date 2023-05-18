package com.example.devhub.webclient.model

import com.example.devhub.model.GitHubRepository

data class GitHubRepositoryWeb(
    val name: String = "",
    val description: String? = null
)
fun GitHubRepositoryWeb.toGitHubRepository() = GitHubRepository(
    name = name,
    description = description ?: ""
)
