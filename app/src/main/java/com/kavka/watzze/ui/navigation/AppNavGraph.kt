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

@Composable
fun AppNavGraph(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController();

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onDone = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true}
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                vm = homeViewModel,
                onGoList = { navController.navigate(Routes.LIST) },
                onGoAdd = { navController.navigate(Routes.ADD_EDIT) },
                onGoWeather = { navController.navigate(Routes.WEATHER) }

            )
        }

        composable(Routes.LIST) {
            ListScreen(
                onBack = { navController.popBackStack() },
                onOpenDetail = { navController.navigate(Routes.detail(id))},
                onAdd = { navController.navigate(Routes.ADD_EDIT)}
            )
        }

        composable(Routes.ADD_EDIT) {
            AddEditScreen(
                onBack = { navController.popBackStack() }
            )
        }

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

        composable(Routes.WEATHER) {
            WeatherScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
