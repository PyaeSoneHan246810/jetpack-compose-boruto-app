package com.example.borutoapp.data.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.borutoapp.domain.manager.PreferencesManager
import com.example.borutoapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesManagerImpl(
    private val context: Context
): PreferencesManager {
    private val dataStore = context.dataStore
    override suspend fun saveAppEntryState(entered: Boolean) {
        dataStore.edit {  pref ->
            pref[APP_ENTRY_KEY] = entered
        }
    }

    override fun readAppEntryState(): Flow<Boolean> {
        return dataStore.data
            .catch {  exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {  pref ->
                pref[APP_ENTRY_KEY] ?: false
            }
    }

    companion object {
        private val APP_ENTRY_KEY = booleanPreferencesKey(
            name = Constants.DATASTORE_KEY_APP_ENTRY
        )
    }
}

private val Context.dataStore by preferencesDataStore(
    name = Constants.DATASTORE_USER_MANAGER
)