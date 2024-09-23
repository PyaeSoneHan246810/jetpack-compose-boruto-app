package com.example.borutoapp.presentation.search.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.borutoapp.domain.usecase.heroes.HeroesUseCases
import com.example.borutoapp.presentation.search.event.SearchEvent
import com.example.borutoapp.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val heroesUseCases: HeroesUseCases
): ViewModel() {
    var searchState by mutableStateOf(SearchState())
        private set


    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                updateSearchQuery(event.newSearchQuery)
            }
            SearchEvent.SearchHeroes -> {
                searchHeroes()
            }
        }
    }

    private fun updateSearchQuery(newSearchQuery: String) {
        searchState = searchState.copy(
            searchQuery = newSearchQuery
        )
    }

    private fun searchHeroes() {
        searchState = searchState.copy(
            heroes = heroesUseCases.searchHeroes.invoke(name = searchState.searchQuery).cachedIn(viewModelScope)
        )
    }
}