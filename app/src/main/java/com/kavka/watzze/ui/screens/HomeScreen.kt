package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kavka.watzze.ui.components.BaseScreen
import com.kavka.watzze.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onGoList: () -> Unit,
    onGoAdd: () -> Unit,
    onGoWeather: () -> Unit
) {
    val state by vm.uiState.collectAsState()

    BaseScreen(
        title = "Domů"
    ) {
        Text(text = "Otužování - Home")
        Spacer(Modifier.height(8.dp))
        Text(text = "Počet záznamů: ${state.totalSession}")

        Spacer(Modifier.height(16.dp))
        Button(onGoAdd) { Text("Přidat záznam") }
        Spacer(Modifier.height(8.dp))
        Button(onGoList) { Text("Jít na list") }
        Spacer(Modifier.height(8.dp))
        Button(onGoWeather) { Text("Počasí") }
    }
}