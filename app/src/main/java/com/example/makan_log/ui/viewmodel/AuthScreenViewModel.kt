package com.example.makan_log.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.network.Supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthScreenViewModel : ViewModel() {

  fun login(
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
  ) {
    viewModelScope.launch {
      try {
        Supabase.client.auth.signInWith(Email) {
          this.email = email
          this.password = password
        }

        println("Login success")
        onSuccess()
      } catch (e: Exception) {
        println("Login failed: ${e.message}")
      }
    }
  }

  fun register(
    username: String,
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
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
        onSuccess()
      } catch (e: Exception) {
        println("Register failed: ${e.message}")
      }
    }
  }
}