package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kavka.watzze.ui.components.BaseScreen

@Composable
fun AddEditScreen(
    onBack: () -> Unit
){
    BaseScreen(
        title = "Přidat nebo editovat",
        onBack = onBack
    ) {
        Text("Přidat/Upravit záznam (placeholder)")
        Button(onBack) { Text("Zpět") }
    }
}