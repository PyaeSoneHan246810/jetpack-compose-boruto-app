package com.example.borutoapp.presentation.details.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.usecase.heroes.HeroesUseCases
import com.example.borutoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val heroesUseCases: HeroesUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var hero by mutableStateOf<Hero?>(null)
        private set

    var colorPalette by mutableStateOf<Map<String, String>>(mapOf())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    init {
        getHero()
    }

    private fun getHero() {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(Constants.ARG_KEY_HERO_ID)
            heroId?.let {  id ->
                hero = heroesUseCases.getHero.invoke(heroId = id)
            }
        }
    }

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun updateColorPalette(colors: Map<String, String>) {
        colorPalette = colors
    }
}

sealed class UiEvent {
    data object GenerateColorPalette: UiEvent()
}