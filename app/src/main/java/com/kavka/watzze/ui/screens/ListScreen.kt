package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kavka.watzze.ui.components.BaseScreen

@Composable
fun ListScreen(
    onBack: () -> Unit,
    onOpenDetail: (Long) -> Unit,
    onAdd: () -> Unit
) {
    BaseScreen(
        title = "List",
        onBack = onBack
    ) {
        Text("Seznam záznamů (placeholder)")
        Button(onClick = onAdd) { Text("Přidat") }
        Button(onClick = {onOpenDetail(1L)}) { Text("Otevřít deatil id = 1") }
        Button(onClick = onBack) { Text("Zpět") }
    }
}