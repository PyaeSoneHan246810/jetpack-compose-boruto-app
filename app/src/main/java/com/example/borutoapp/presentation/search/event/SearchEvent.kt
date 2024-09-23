package com.example.borutoapp.presentation.search.event

sealed class SearchEvent {
    data class UpdateSearchQuery(val newSearchQuery: String): SearchEvent()
    data object SearchHeroes: SearchEvent()
}