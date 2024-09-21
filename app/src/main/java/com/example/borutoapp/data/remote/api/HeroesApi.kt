package com.example.borutoapp.data.remote.api

import com.example.borutoapp.data.remote.dto.HeroesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {
    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): HeroesResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): HeroesResponse
}