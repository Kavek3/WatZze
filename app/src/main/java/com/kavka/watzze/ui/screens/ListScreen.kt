package com.kavka.watzze.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kavka.watzze.ui.components.BaseScreen
import com.kavka.watzze.viewmodel.SessionsListViewModel

/**
 * List Screen to show all sessions in app
 */
@Composable
fun ListScreen(
    vm: SessionsListViewModel,
    onBack: () -> Unit,
    onOpenDetail: (Long) -> Unit,
    onAdd: () -> Unit
) {
    val sessions by vm.sessions.collectAsState()
    BaseScreen(
        title = "List",
        onBack = onBack
    ) {
        Button(
            onClick = onAdd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add record")
        }

        Spacer(Modifier.height(16.dp))

        if(sessions.isEmpty()) {
            Text("Still nothing")
        } else {
            LazyColumn {
                items(sessions) { session -> Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                onOpenDetail(session.id)
                            }
                    ) {
                        Text("Délka ${session.durationSeconds} s")
                        session.waterTempC?.let {
                            Text("Teplota vody: $it °C")
                        }
                        session.note?.let { Text(it) }
                }
                }
            }
        }
    }
}