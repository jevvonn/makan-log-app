package com.example.makan_log.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.makan_log.core.auth.AuthRepository
import com.example.makan_log.feature.auth.AuthScreen
import com.example.makan_log.feature.auth.AuthViewModel
import com.example.makan_log.feature.home.HomeScreen
import androidx.compose.runtime.getValue
import com.example.makan_log.core.auth.AuthState
import com.example.makan_log.feature.splash.SplashScreen

@Composable
fun NavGraph() {
  val navController = rememberNavController()
  val authState by AuthRepository.state.collectAsStateWithLifecycle()

  NavHost(
    navController = navController,
    startDestination = Route.Splash,
  ) {
    composable(Route.Splash) {
      SplashScreen()

      LaunchedEffect(authState) {
        when (authState) {
          AuthState.Loading -> Unit
          else -> {
            navController.navigate(Route.Home) {
              popUpTo(Route.Splash) { inclusive = true }
            }
          }
        }
      }
    }

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

