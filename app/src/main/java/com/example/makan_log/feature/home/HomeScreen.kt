package com.example.makan_log.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.makan_log.core.auth.AuthRepository
import com.example.makan_log.feature.auth.AuthViewModel

@Composable
fun HomeScreen(
  authViewModel: AuthViewModel = viewModel(),
  onNavigateToLogin: () -> Unit,
  onNavigateToRegister: () -> Unit,
) {
  val authUser = AuthRepository.user.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = "MakanLog",
      style = MaterialTheme.typography.headlineLarge,
    )

    Text(authUser.value?.email ?: "No user logged in")

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Track your meals, every day.",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Spacer(modifier = Modifier.height(48.dp))

    Button(
      onClick = onNavigateToLogin,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(text = "Login")
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButton(
      onClick = onNavigateToRegister,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(text = "Register")
    }

    OutlinedButton(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        authViewModel.logout(
          onSuccess = { onNavigateToLogin() },
          onError = { println("Logout failed: ${it.message}") },
        )
      },
    ) {
      Text(text = "Logout")
    }
  }
}

