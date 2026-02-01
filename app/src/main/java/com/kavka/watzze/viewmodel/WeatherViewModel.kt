package com.kavka.watzze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavka.watzze.data.weather.WeatherModel
import com.kavka.watzze.data.weather.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Weather ui state class
 */
data class WeatherUiState(
    val isLoading : Boolean = false,
    val data: WeatherModel? = null,
    val error: String? = null
)

/**
 * Weather ViewModel for managing states in Ui and models
 */
class WeatherViewModel(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    /**
     * load weather from rest api
     * @param [latitude] latitude for location
     * @param [longitude] longitude for location
     */
    fun loadWeather(latitude: Double, longitude: Double) {
       _uiState.value = WeatherUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val weather = repository.getCurrentWeather(latitude, longitude)
                _uiState.value = WeatherUiState(
                    isLoading = false,
                    data = weather
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState(
                    isLoading = false,
                    error = e.message ?: "Nelze načíst data ze serveru!"
                )
            }
        }
    }
}