package com.kavka.watzze.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kavka.watzze.ui.components.BaseScreen

@Composable
fun DeatilScreen(
    sessionId: Long,
    onBack: () -> Unit
) {
    BaseScreen(
        title = "Detail",
        onBack = onBack
    ) {
        Text("Detail záznamu id=$sessionId (placeholder)")
        Button(onBack) { Text("Zpět") }
    }
}