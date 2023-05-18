package com.example.devhub.webclient

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.devhub.model.toProfileUiState
import com.example.devhub.ui.screen.ProfileUiState
import com.example.devhub.webclient.model.toGitHubRepository

class GitHubWebClient(
    private val service: GitHubService = RetrofitInitializer().gitHubService
) {
    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun findProfileBy(user: String) {
        try {
            val profile = service.findProfileBy(user).toProfileUiState()
            val repositories = service.findRepositoriesBy(user).map { it.toGitHubRepository() }
            uiState = profile.copy(repositories = repositories)
        }
        catch (e:Exception){
            Log.e("GitHubWebClient", "findProfileBy: Falha ao buscar ussuario", e)
        }
    }

}