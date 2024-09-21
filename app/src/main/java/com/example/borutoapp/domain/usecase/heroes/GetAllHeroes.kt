package com.example.borutoapp.domain.usecase.heroes

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.HeroesDataSource
import kotlinx.coroutines.flow.Flow

class GetAllHeroes(
    private val heroesDataSource: HeroesDataSource
) {
    operator fun invoke(): Flow<PagingData<Hero>> = heroesDataSource.getAllHeroes()
}