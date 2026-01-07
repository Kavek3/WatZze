package com.kavka.watzze.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kavka.watzze.viewmodel.HomeViewModel

@Composable
fun HomeScreen(vm: HomeViewModel) {
    val state by vm.uiState.collectAsState()
    Text(text = "Záznamů ${state.totalSession}")
}