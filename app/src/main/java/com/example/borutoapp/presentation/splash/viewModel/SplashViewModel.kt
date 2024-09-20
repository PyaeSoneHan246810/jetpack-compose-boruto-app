package com.example.borutoapp.presentation.splash.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.usecase.app_entry_state.AppEntryStateUseCases
import com.example.borutoapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (
    private val appEntryStateUseCases: AppEntryStateUseCases
): ViewModel() {
    var destinationRoute by mutableStateOf(Screen.Splash.route)
        private set

    init {
        readAppEntryState()
    }
    private fun readAppEntryState() {
        appEntryStateUseCases.readAppEntryState().onEach {  entered ->
            destinationRoute = if (entered) Screen.Home.route else Screen.Welcome.route
        }.launchIn(viewModelScope)
    }
}