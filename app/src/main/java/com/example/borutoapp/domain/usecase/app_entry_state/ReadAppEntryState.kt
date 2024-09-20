package com.example.borutoapp.domain.usecase.app_entry_state

import com.example.borutoapp.domain.manager.PreferencesManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryState(
    private val preferencesManager: PreferencesManager
) {
    operator fun invoke(): Flow<Boolean> = preferencesManager.readAppEntryState()
}