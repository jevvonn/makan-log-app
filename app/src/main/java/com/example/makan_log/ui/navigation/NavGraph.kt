package com.example.makan_log.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.makan_log.core.auth.AuthState
import com.example.makan_log.feature.bookmark.BookmarkScreen
import com.example.makan_log.feature.create.CreateScreen
import com.example.makan_log.feature.profile.ProfileScreen
import com.example.makan_log.feature.splash.SplashScreen
import com.example.makan_log.ui.theme.Cream

@Composable
fun NavGraph() {
  val navController = rememberNavController()
  val authState by AuthRepository.state.collectAsStateWithLifecycle()

  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route
  val showNavBar = currentRoute in routesWithBottomBar

  Scaffold(
    bottomBar = {
      if (showNavBar) {
        NavBar(navController)
      }
    }
  ) { paddingValues ->

    NavHost(
      navController = navController,
      startDestination = Route.Splash,
      modifier = Modifier
        .padding(paddingValues = paddingValues)
        .background(Cream)
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

      composable(Route.Bookmark) {
        BookmarkScreen()
      }

      composable(Route.Create) {
        CreateScreen(
          onNavigateBack = { navController.popBackStack() },
          onNavigateToHome = {
            navController.navigate(Route.Home) {
              popUpTo(Route.Create) { inclusive = true }
            }
          }
        )
      }

      composable(Route.Profile) {
        ProfileScreen()
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
}

