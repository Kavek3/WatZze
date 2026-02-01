package com.kavka.watzze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavka.watzze.data.model.ColdSession
import com.kavka.watzze.data.repository.ColdSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * UI state for the Add/Edit session screen.
 */
data class AddEditUiState (
    val id: Long? = null,
    val durationText: String = "",
    val tempText: String = "",
    val note: String = "",
    val isSaving: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel responsible for managing the Add/Edit session screen.
 * Uses [StateFlow] for reactive UI updates in Jetpack Compose.
 */
class AddEditViewModel(
    private val repository: ColdSessionRepository
): ViewModel() {

    // UI state flow
    private val _uiState = MutableStateFlow(AddEditUiState())
    val uiState: StateFlow<AddEditUiState> = _uiState

    /** Updates the session duration input field. */
    fun onDurationChange(value: String) {
        _uiState.value = _uiState.value.copy(durationText = value)
    }

    /** Updates the water temperature input field. */
    fun onTempTextChange(value: String) {
        _uiState.value = _uiState.value.copy(tempText = value)
    }

    /** Updates the note input field. */
    fun onNoteChange(value: String) {
        _uiState.value = _uiState.value.copy(note = value)
    }

    /**
     * Loads an existing session into the form for editing.
     * Converts numeric fields to text so they can be edited in the UI.
     */
    fun loadForEdit(session: ColdSession) {
        _uiState.value = AddEditUiState(
            id = session.id,
            durationText = session.durationSeconds.toString(),
            tempText = session.waterTempC?.toString() ?: "",
            note = session.note.orEmpty()
        )
    }

    /**
     * Validates the form and saves a new or existing session.
     */
    fun save(onDone: () -> Unit) {
        val state = _uiState.value
        val duration = state.durationText.toIntOrNull()
        val temp = state.tempText.toFloatOrNull()

        if (duration == null || duration <= 0) {
            _uiState.value = state.copy(error = "Enter the length in seconds")
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isSaving = true, error = null)

            val session = ColdSession(
                id = state.id ?: 0L,
                durationSeconds = duration,
                waterTempC = temp,
                note = state.note.ifBlank { null }
            )
            repository.upsert(session)

            _uiState.value = AddEditUiState()
            onDone()
        }
    }
}