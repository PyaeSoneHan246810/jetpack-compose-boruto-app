package com.example.borutoapp.domain.usecase.heroes

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.HeroesDataSource
import kotlinx.coroutines.flow.Flow

class SearchHeroes(
    private val heroesDataSource: HeroesDataSource
) {
    operator fun invoke(name: String): Flow<PagingData<Hero>> = heroesDataSource.searchHeroes(name)
}