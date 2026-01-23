package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kavka.watzze.ui.components.BaseScreen

@Composable
fun WeatherScreen(
    onBack: () -> Unit
) {
    BaseScreen(
        title = "Počasí",
        onBack = onBack
    ) {
        Text("Počasí (placeholder)")
        Button(onBack) { Text("Zpět") }
    }
}