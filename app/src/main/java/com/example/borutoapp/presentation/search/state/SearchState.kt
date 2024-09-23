package com.example.borutoapp.presentation.search.state

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val heroes: Flow<PagingData<Hero>>? = null
)
