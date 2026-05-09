package com.example.makan_log.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.auth.AuthRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class AuthViewModel : ViewModel() {
  var isLoading by mutableStateOf(false)
    private set

  fun login(
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
    onError: (Exception) -> Unit = {},
  ) {
    viewModelScope.launch {
      isLoading = true

      try {
        AuthRepository.login(email, password)
        println("Login success")
        onSuccess()
      } catch (e: Exception) {
        println("Login failed: ${e.message}")
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }

  fun register(
    username: String,
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
    onError: (Exception) -> Unit = {},
  ) {
    viewModelScope.launch {
      isLoading = true

      try {
        AuthRepository.register(username, email, password)
        println("Register success")
        onSuccess()
      } catch (e: Exception) {
        println("Register failed: ${e.message}")
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }

  fun logout(
    onSuccess: () -> Unit = {},
    onError: (Exception) -> Unit = {},
  ) {
    viewModelScope.launch {
      try {
        AuthRepository.logout()
        println("Logout success")
        onSuccess()
      } catch (e: Exception) {
        println("Logout failed: ${e.message}")
        onError(e)
      }
    }
  }
}

