package com.kavka.watzze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavka.watzze.data.repository.ColdSessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val totalSession: Int = 0
)

class HomeViewModel (
    repository: ColdSessionRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        repository.observeSession()
            .map { HomeUiState(it.size) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                HomeUiState()
            )
}