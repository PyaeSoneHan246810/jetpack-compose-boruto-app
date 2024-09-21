package com.example.borutoapp.presentation.home.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.usecase.heroes.HeroesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val heroesUseCases: HeroesUseCases
): ViewModel() {
    var heroes by mutableStateOf<Flow<PagingData<Hero>>?>(null)
        private set

    init {
        getAllHeroes()
    }

    private fun getAllHeroes() {
        heroes = heroesUseCases.getAllHeroes.invoke()
            .cachedIn(viewModelScope)
    }
}