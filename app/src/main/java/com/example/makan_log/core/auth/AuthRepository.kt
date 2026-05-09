package com.example.makan_log.core.auth

import com.example.makan_log.core.network.Supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object AuthRepository {
  private val _user = MutableStateFlow<UserInfo?>(null)
  val user = _user.asStateFlow()

  suspend fun login(email: String, password: String) {
    Supabase.client.auth.signInWith(Email) {
      this.email = email
      this.password = password
    }
    refresh()
  }

  suspend fun register(username: String, email: String, password: String) {
    Supabase.client.auth.signUpWith(Email) {
      this.email = email
      this.password = password
      this.data = buildJsonObject { put("username", username) }
    }
    refresh()
  }

  suspend fun logout() {
    Supabase.client.auth.signOut()
    _user.value = null
  }

  private suspend fun refresh() {
    try {
      _user.value = Supabase.client.auth.retrieveUserForCurrentSession(true)
    } catch (_: Exception) {
      _user.value = null
    }
  }
}

