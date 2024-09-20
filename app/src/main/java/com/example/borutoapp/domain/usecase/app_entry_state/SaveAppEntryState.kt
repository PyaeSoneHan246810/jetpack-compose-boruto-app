package com.example.borutoapp.domain.usecase.app_entry_state

import com.example.borutoapp.domain.manager.PreferencesManager

class SaveAppEntryState(
    private val preferencesManager: PreferencesManager
) {
    suspend operator fun invoke(entered: Boolean) = preferencesManager.saveAppEntryState(entered)
}