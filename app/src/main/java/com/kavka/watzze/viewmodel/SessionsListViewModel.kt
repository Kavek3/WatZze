package com.kavka.watzze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavka.watzze.data.model.ColdSession
import com.kavka.watzze.data.repository.ColdSessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel for the screen that displays a list of all cold sessions.
 */
class SessionsListViewModel(
    repository: ColdSessionRepository
): ViewModel() {

    /**
     * Reactive list of all sessions stored in the app.
     */
    val sessions: StateFlow<List<ColdSession>> =
        repository.observeSessions()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )
}