package com.example.makan_log.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.makan_log.feature.auth.AuthScreen
import com.example.makan_log.feature.auth.AuthViewModel
import com.example.makan_log.feature.home.HomeScreen

@Composable
fun NavGraph() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Route.Home,
  ) {
    composable(Route.Home) {
      HomeScreen(
        onNavigateToLogin = { navController.navigate(Route.AuthLogin) },
        onNavigateToRegister = { navController.navigate(Route.AuthRegister) },
      )
    }

    composable(
      route = Route.Auth,
      arguments = listOf(navArgument("tab") { type = NavType.StringType }),
    ) { backStackEntry ->
      val tab = backStackEntry.arguments?.getString("tab") ?: "login"

      val authViewModel: AuthViewModel = viewModel()

      AuthScreen(
        startOnLogin = tab == "login",
        authViewModel = authViewModel,
        onNavigateToHome = { navController.navigate(Route.Home) },
      )
    }
  }
}

