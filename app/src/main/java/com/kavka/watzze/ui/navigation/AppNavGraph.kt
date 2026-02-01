package com.kavka.watzze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kavka.watzze.viewmodel.HomeViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kavka.watzze.ui.screens.AddEditScreen
import com.kavka.watzze.ui.screens.DeatilScreen
import com.kavka.watzze.ui.screens.HomeScreen
import com.kavka.watzze.ui.screens.SplashScreen
import com.kavka.watzze.ui.screens.ListScreen
import com.kavka.watzze.ui.screens.WeatherScreen
import com.kavka.watzze.viewmodel.AddEditViewModel
import com.kavka.watzze.viewmodel.SessionsListViewModel
import com.kavka.watzze.viewmodel.WeatherViewModel

/**
 * Main navigation graph for the application.
 *
 *
 * @param homeViewModel ViewModel for the Home screen.
 * @param listViewModel ViewModel for the list of sessions.
 * @param addEditViewModel ViewModel for adding or editing a session.
 * @param weatherViewModel ViewModel for weather
 * @param modifier Optional modifier for customizing NavHost layout.
 */
@Composable
fun AppNavGraph(
    homeViewModel: HomeViewModel,
    listViewModel: SessionsListViewModel,
    addEditViewModel: AddEditViewModel,
    weatherViewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController();

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {
        // --- Splash screen ---
        composable(Routes.SPLASH) {
            SplashScreen(
                onDone = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true}
                    }
                }
            )
        }

        // --- Home screen ---
        composable(Routes.HOME) {
            HomeScreen(
                vm = homeViewModel,
                onGoList = { navController.navigate(Routes.LIST) },
                onGoAdd = { navController.navigate(Routes.ADD_EDIT) },
                onGoWeather = { navController.navigate(Routes.WEATHER) }

            )
        }

        // --- List screen ---
        composable(Routes.LIST) {
            ListScreen(
                vm = listViewModel,
                onBack = { navController.popBackStack() },
                onOpenDetail = { navController.navigate(Routes.detail(id))},
                onAdd = { navController.navigate(Routes.ADD_EDIT)}
            )
        }

        // --- Add / Edit screen ---
        composable(Routes.ADD_EDIT) {
            AddEditScreen(
                vm = addEditViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // --- Detail screen ---
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
          backStackEntry -> val id = backStackEntry.arguments?.getLong("id") ?: 0L
            DeatilScreen(
                sessionId = id,
                onBack = { navController.popBackStack() }
            )
        }

        // --- Weather screen ---
        composable(Routes.WEATHER) {
            WeatherScreen(
                vm = weatherViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
