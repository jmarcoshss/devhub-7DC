package com.example.devhub.webclient

import android.util.Log
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class GitHubWebClient(
    private val service: GitHubService = RetrofitInitializer().gitHubService
) {
    fun findProfileBy(user:String) = flow {
        try {
            emit(service.findProfileBy(user))
        }
        catch (e:Exception){
            Log.e("GitHubWebClient", "findProfileBy: Falha ao buscar ussuario", e)
        }
    }

}