package com.example.borutoapp.presentation.welcome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.usecase.app_entry_state.AppEntryStateUseCases
import com.example.borutoapp.presentation.welcome.event.WelcomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val appEntryStateUseCases: AppEntryStateUseCases
): ViewModel() {
    fun onEvent(event: WelcomeEvent) {
        when(event) {
            WelcomeEvent.SaveAppEntryState -> {
                saveAppEntryState()
            }
        }
    }

    private fun saveAppEntryState() {
        viewModelScope.launch(Dispatchers.IO) {
            appEntryStateUseCases.saveAppEntryState(entered = true)
        }
    }
}