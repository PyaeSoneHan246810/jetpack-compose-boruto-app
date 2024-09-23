package com.example.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoapp.data.local.database.HeroesDatabase
import com.example.borutoapp.data.paging.HeroRemoteMediator
import com.example.borutoapp.data.paging.SearchHeroesPagingSource
import com.example.borutoapp.data.remote.api.HeroesApi
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.HeroesDataSource
import com.example.borutoapp.util.Constants
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class HeroesDataSourceImpl(
    private val heroesApi: HeroesApi,
    private val heroesDatabase: HeroesDatabase
): HeroesDataSource {
    private val heroesDao = heroesDatabase.heroDao

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_PAGE_SIZE
            ),
            remoteMediator = HeroRemoteMediator(
                heroesApi = heroesApi,
                heroesDatabase = heroesDatabase
            ),
            pagingSourceFactory = {
                heroesDao.getAllHeroes()
            }
        )
        return pager.flow
    }

    override fun searchHeroes(name: String): Flow<PagingData<Hero>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                SearchHeroesPagingSource(
                    heroesApi = heroesApi,
                    name = name,
                )
            }
        )
        return pager.flow
    }
}