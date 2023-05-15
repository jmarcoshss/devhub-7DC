package com.example.devhub.webclient

import com.example.devhub.model.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")
    suspend fun findProfileBy(@Path("user") user: String): GitHubUser

}