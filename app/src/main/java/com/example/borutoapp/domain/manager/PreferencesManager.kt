package com.example.borutoapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    suspend fun saveAppEntryState(entered: Boolean)
    fun readAppEntryState(): Flow<Boolean>
}