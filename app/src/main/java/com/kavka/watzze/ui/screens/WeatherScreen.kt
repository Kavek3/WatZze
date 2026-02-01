package com.kavka.watzze.ui.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
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

    val context = LocalContext.current
    val activity = context as? Activity

    // Fused location client to get location
    val fusedLocationClient = rememberSaveable(
        saver = androidx.compose.runtime.saveable.Saver(
            save = { null },
            restore = { LocationServices.getFusedLocationProviderClient(context) }
        )
    ) {
        LocationServices.getFusedLocationProviderClient(context)
    }

    // To dont ask on every tap on weather
    var hasRequestedPermission by rememberSaveable { mutableStateOf(false) }

    // Launcher for runtime permission dialog
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            // if user allow location try to get last location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->

                    if (location != null) {
                        vm.loadWeather(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                    } else {
                        vm.setError("Nepodařilo se získat aktuální polohu")
                    }
                }
                .addOnFailureListener {
                    vm.setError("Chyba při získávání polohy: ${it.message}")
                }
        } else {
            vm.setError("Bez povolení polohy neumím číst počasí")
        }
    }

    /** Request for weather **/
    LaunchedEffect(Unit) {
        if (!hasRequestedPermission) {
            hasRequestedPermission = true

            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (granted) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->

                        if (location != null) {
                            vm.loadWeather(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        } else {
                            vm.setError("Nepodařilo se získat aktuální polohu")
                        }
                    }
                    .addOnFailureListener {
                        vm.setError("Chyba při získávání polohy: ${it.message}")
                    }
            } else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        // location for Prague
        // vm.loadWeather(latitude = 50.00, longitude = 14.44)
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