package com.example.borutoapp.data.remote.dto

import com.example.borutoapp.domain.model.Hero
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Hero> = emptyList(),
)