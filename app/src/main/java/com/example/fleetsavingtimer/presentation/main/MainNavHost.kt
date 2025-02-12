package com.example.fleetsavingtimer.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fleetsavingtimer.presentation.settings.SettingsScreen
import com.example.fleetsavingtimer.ui.theme.primary

@Composable
internal fun MainNavHost(
    onClose: () -> Unit = { },
) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screens.Main.route
        ) {
            composable(Screens.Main.route) {
                MainScreen(
                    onClose = onClose,
                    goToSettings = {
                        navController.navigate(Screens.Settings.route)
                    }
                )
            }
            composable(Screens.Settings.route) {
                SettingsScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object Main: Screens("main")
    data object Settings: Screens("settings")
}