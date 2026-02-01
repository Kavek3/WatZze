package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kavka.watzze.ui.components.BaseScreen
import com.kavka.watzze.viewmodel.WeatherViewModel

/**
 * Weather screen to see weather from api
 */
@Composable
fun WeatherScreen(
    vm: WeatherViewModel,
    onBack: () -> Unit
) {
    // ui state
    val state by vm.uiState.collectAsState()

    /** Request for weather **/
    LaunchedEffect(Unit) {
        vm.loadWeather(latitude = 50.00, longitude = 14.44)
    }

    BaseScreen(
        title = "Počasí",
        onBack = onBack
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
            Spacer(Modifier.height(16.dp))
        }

        state.error?.let { errorMsg ->
            Text(text = errorMsg, color = Color.Red)
            Spacer(Modifier.height(16.dp))
        }

        state.data?.let { weather ->
            Column {
                Text("Teplota: ${"%.1f".format(weather.temperatureC)} °C")
                Spacer(Modifier.height(4.dp))
                Text("Vlhkost: ${"%.0f".format(weather.humidity)} %")
                Spacer(Modifier.height(4.dp))
                Text("Vítr: ${"%.1f".format(weather.windSpeed)} m/s")
            }
        }

        Button(onBack) { Text("Zpět") }
    }
}