package com.kavka.watzze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kavka.watzze.data.repository.FakeColdSessionRepository
import com.kavka.watzze.ui.screens.HomeScreen
import com.kavka.watzze.ui.theme.WatZzeTheme
import com.kavka.watzze.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repo = FakeColdSessionRepository()
        val vm = HomeViewModel(repo)
        setContent {
            WatZzeTheme {
                HomeScreen(vm)
            }
        }
    }
}