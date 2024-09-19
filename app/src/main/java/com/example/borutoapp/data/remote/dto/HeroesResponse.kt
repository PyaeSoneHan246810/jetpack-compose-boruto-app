package com.example.borutoapp.data.remote.dto

import com.example.borutoapp.domain.model.Hero

data class HeroesResponse(
    val success: Boolean,
    val message: String,
    val prevPage: Any,
    val nextPage: Int,
    val heroes: List<Hero>,
)