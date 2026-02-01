package com.kavka.watzze.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kavka.watzze.ui.components.BaseScreen
import com.kavka.watzze.viewmodel.AddEditViewModel
import java.nio.file.WatchEvent

/**
 * Add / Edit screen to create or edit session
 */
@Composable
fun AddEditScreen(
    vm: AddEditViewModel,
    onBack: () -> Unit
){
    val state by vm.uiState.collectAsState()

    BaseScreen(
        title = "Přidat / editovat záznam",
        onBack = onBack
    ) {
        if (state.error != null) {
            Text( text = state.error!!, color = Color.Red )
            Spacer(Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = state.durationText,
            onValueChange = vm::onDurationChange,
            label = { Text("Délka otužování (s)")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.tempText,
            onValueChange = vm::onTempTextChange,
            label = { Text("Teplota vody (°C)")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.note,
            onValueChange = vm::onNoteChange,
            label = { Text("Poznámka")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { vm.save(onBack)},
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving
        ) {
            Text("Save")
        }
    }
}