package com.example.borutoapp.domain.usecase.heroes

import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.HeroesDataSource

class GetHero(
    private val heroesDataSource: HeroesDataSource
) {
    suspend operator fun invoke(heroId: Int): Hero = heroesDataSource.getHero(heroId)
}