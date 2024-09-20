package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.manager.PreferencesManagerImpl
import com.example.borutoapp.domain.manager.PreferencesManager
import com.example.borutoapp.domain.usecase.app_entry_state.AppEntryStateUseCases
import com.example.borutoapp.domain.usecase.app_entry_state.ReadAppEntryState
import com.example.borutoapp.domain.usecase.app_entry_state.SaveAppEntryState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ): PreferencesManager {
        return PreferencesManagerImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideAppEntryStateUseCases(
        preferencesManager: PreferencesManager
    ): AppEntryStateUseCases {
        return AppEntryStateUseCases(
            saveAppEntryState = SaveAppEntryState(
                preferencesManager = preferencesManager
            ),
            readAppEntryState = ReadAppEntryState(
                preferencesManager = preferencesManager
            )
        )
    }
}