package com.example.borutoapp.domain.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroesDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>

    fun searchHeroes(name: String): Flow<PagingData<Hero>>

    suspend fun getHero(heroId: Int): Hero
}