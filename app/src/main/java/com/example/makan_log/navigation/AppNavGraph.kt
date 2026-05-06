package com.example.makan_log.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.makan_log.ui.screen.HomeScreen
import com.example.makan_log.ui.screen.LoginScreen
import com.example.makan_log.ui.screen.RegisterScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable(Routes.Home) {
            HomeScreen(
                onNavigateToLogin = { navController.navigate(Routes.Login) },
                onNavigateToRegister = { navController.navigate(Routes.Register) }
            )
        }

        composable(Routes.Login) {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onNavigateToRegister = { navController.navigate(Routes.Register) }
            )
        }

        composable(Routes.Register) {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onNavigateToLogin = { navController.navigate(Routes.Login) }
            )
        }
    }
}

