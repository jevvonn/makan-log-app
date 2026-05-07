package com.example.makan_log.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.makan_log.core.model.AuthSession
import com.example.makan_log.ui.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
  onNavigateToLogin: () -> Unit,
  onNavigateToRegister: () -> Unit
) {
  val authUser = AuthSession.user.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "MakanLog",
      style = MaterialTheme.typography.headlineLarge
    )

    Text(authUser.value?.email ?: "No user logged in")

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Track your meals, every day.",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(48.dp))

    Button(
      onClick = onNavigateToLogin,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Login")
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButton(
      onClick = onNavigateToRegister,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Register")
    }
  }
}

