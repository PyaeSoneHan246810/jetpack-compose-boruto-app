package com.example.borutoapp.presentation.welcome.event

sealed class WelcomeEvent {
    data object SaveAppEntryState: WelcomeEvent()
}