package com.example.borutoapp.di

import com.example.borutoapp.domain.manager.PreferencesManager
import com.example.borutoapp.domain.repository.HeroesDataSource
import com.example.borutoapp.domain.usecase.app_entry_state.AppEntryStateUseCases
import com.example.borutoapp.domain.usecase.app_entry_state.ReadAppEntryState
import com.example.borutoapp.domain.usecase.app_entry_state.SaveAppEntryState
import com.example.borutoapp.domain.usecase.heroes.GetAllHeroes
import com.example.borutoapp.domain.usecase.heroes.HeroesUseCases
import com.example.borutoapp.domain.usecase.heroes.SearchHeroes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideAppEntryStateUseCases(
        preferencesManager: PreferencesManager
    ): AppEntryStateUseCases {
        return AppEntryStateUseCases(
            saveAppEntryState = SaveAppEntryState(
                preferencesManager = preferencesManager
            ),
            readAppEntryState = ReadAppEntryState(
                preferencesManager = preferencesManager
            )
        )
    }

    @Provides
    @Singleton
    fun provideHeroesUseCases(
        heroesDataSource: HeroesDataSource
    ): HeroesUseCases {
        return HeroesUseCases(
            getAllHeroes = GetAllHeroes(
                heroesDataSource = heroesDataSource
            ),
            searchHeroes = SearchHeroes(
                heroesDataSource = heroesDataSource
            )
        )
    }
}