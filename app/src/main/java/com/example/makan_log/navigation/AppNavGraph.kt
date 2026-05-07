package com.example.makan_log.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.makan_log.ui.screen.AuthScreen
import com.example.makan_log.ui.screen.HomeScreen
import com.example.makan_log.ui.viewmodel.AuthScreenViewModel

@Composable
fun AppNavGraph() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Routes.Home,
  ) {
    composable(Routes.Home) {
      HomeScreen(
        onNavigateToLogin = { navController.navigate(Routes.AuthLogin) },
        onNavigateToRegister = { navController.navigate(Routes.AuthRegister) },
      )
    }

    composable(
      route = Routes.Auth,
      arguments = listOf(navArgument("tab") { type = NavType.StringType }),
    ) { backStackEntry ->
      val tab = backStackEntry.arguments?.getString("tab") ?: "login"

      val vmAuthScreen: AuthScreenViewModel = viewModel()

      AuthScreen(
        startOnLogin = tab == "login",
        authViewModel = vmAuthScreen,
        onNavigateToHome = { navController.navigate(Routes.Home) },
      )
    }
  }
}
