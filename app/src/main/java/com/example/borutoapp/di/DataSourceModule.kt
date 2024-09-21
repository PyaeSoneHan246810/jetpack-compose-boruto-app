package com.example.borutoapp.di

import com.example.borutoapp.data.local.database.HeroesDatabase
import com.example.borutoapp.data.remote.api.HeroesApi
import com.example.borutoapp.data.repository.HeroesDataSourceImpl
import com.example.borutoapp.domain.repository.HeroesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideHeroesDataSource(
        heroesApi: HeroesApi,
        heroesDatabase: HeroesDatabase
    ): HeroesDataSource {
        return HeroesDataSourceImpl(
            heroesApi = heroesApi,
            heroesDatabase = heroesDatabase,
        )
    }
}