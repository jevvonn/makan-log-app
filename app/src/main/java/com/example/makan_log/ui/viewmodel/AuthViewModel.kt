package com.example.makan_log.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.model.AuthSession
import com.example.makan_log.core.network.Supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthViewModel : ViewModel() {
  fun login(
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
    onError: (e: Error) -> Unit = {},
  ) {
    viewModelScope.launch {
      try {
        Supabase.client.auth.signInWith(Email) {
          this.email = email
          this.password = password
        }

        println("Login success")
        updateCurrentUser()
        onSuccess()
      } catch (e: Exception) {
        println("Login failed: ${e.message}")
        onError(Error(e.message ?: "Unknown error"))
      }
    }
  }

  fun register(
    username: String,
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
    onError: (e: Error) -> Unit = {},
  ) {
    viewModelScope.launch {
      try {
        Supabase.client.auth.signUpWith(Email) {
          this.email = email
          this.password = password
          this.data = buildJsonObject {
            put("username", username)
          }
        }

        println("Register success")
        updateCurrentUser()
        onSuccess()
      } catch (e: Exception) {
        println("Register failed: ${e.message}")
        onError(Error(e.message ?: "Unknown error"))
      }
    }
  }

  fun logout(
    onSuccess: () -> Unit = {},
    onError: (e: Error) -> Unit = {}
  ) {
    viewModelScope.launch {
      try {
        Supabase.client.auth.signOut()

        println("Logout success")
        updateCurrentUser()
        onSuccess()
      } catch (e: Exception) {
        println("Logout failed: ${e.message}")
        onError(Error(e.message ?: "Unknown error"))
      }
    }
  }

  private suspend fun updateCurrentUser() {
    try {
      val user = Supabase.client.auth.retrieveUserForCurrentSession(true)
      AuthSession.update(user)
    } catch (e: Exception) {
      AuthSession.update(null)
    }
  }
}