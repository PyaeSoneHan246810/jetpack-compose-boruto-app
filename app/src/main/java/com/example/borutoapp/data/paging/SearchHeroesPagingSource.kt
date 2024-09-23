package com.example.borutoapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.borutoapp.data.remote.api.HeroesApi
import com.example.borutoapp.domain.model.Hero

class SearchHeroesPagingSource(
    private val heroesApi: HeroesApi,
    private val name: String
): PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val heroesResponse = heroesApi.searchHeroes(name)
            val heroes = heroesResponse.heroes
            LoadResult.Page(
                data = heroes.ifEmpty { emptyList() },
                nextKey = null,
                prevKey = null,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}