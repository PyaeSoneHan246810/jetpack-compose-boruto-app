package com.example.borutoapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutoapp.data.local.database.HeroesDatabase
import com.example.borutoapp.data.remote.api.HeroesApi
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.model.HeroRemoteKey
import com.example.borutoapp.util.Constants
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val heroesApi: HeroesApi,
    private val heroesDatabase: HeroesDatabase
) : RemoteMediator<Int, Hero>() {
    private val heroDao = heroesDatabase.heroDao
    private val heroRemoteKeyDao = heroesDatabase.heroRemoteKeyDao

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeyDao.getRemoteKey(id = 1)?.lastUpdated ?: 0L
        val diffInMinutes = ((currentTime - lastUpdated) / 1000 / 60).toInt()
        val cacheTimeOutInMinutes = Constants.CACHE_TIMEOUT_IN_MINUTES
        return if (diffInMinutes <= cacheTimeOutInMinutes) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page: Int = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    remoteKey?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
            }
            val heroesResponse = heroesApi.getAllHeroes(page)
            if (heroesResponse.heroes.isNotEmpty()) {
                heroesDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroRemoteKeyDao.deleteAllRemoteKeys()
                        heroDao.deleteAllHeroes()
                    }
                    val heroRemoteKeys = heroesResponse.heroes.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            prevPage = heroesResponse.prevPage,
                            nextPage = heroesResponse.nextPage,
                            lastUpdated = heroesResponse.lastUpdated
                        )
                    }
                    heroRemoteKeyDao.saveAllRemoteKeys(heroRemoteKeys)
                    heroDao.saveAllHeroes(heroesResponse.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = heroesResponse.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.anchorPosition?.let {  position ->
            state.closestItemToPosition(position)?.id?.let {  id ->
                heroRemoteKeyDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {  hero ->
            heroRemoteKeyDao.getRemoteKey(hero.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {  hero ->
            heroRemoteKeyDao.getRemoteKey(hero.id)
        }
    }
}