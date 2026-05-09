package com.example.makan_log.core.auth

import com.example.makan_log.core.network.Supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

sealed interface AuthState {
  object Loading : AuthState        // sedang restore session dari storage
  object Unauthenticated : AuthState
  data class Authenticated(val user: UserInfo) : AuthState
}

object AuthRepository {
  private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

  private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
  val state = _state.asStateFlow()

  val user: UserInfo?
    get() = (_state.value as? AuthState.Authenticated)?.user

  init {
    scope.launch {
      Supabase.client.auth.sessionStatus.collect { status ->
        _state.value = when (status) {
          is SessionStatus.Initializing -> AuthState.Loading
          is SessionStatus.Authenticated -> AuthState.Authenticated(status.session.user!!)
          is SessionStatus.NotAuthenticated -> AuthState.Unauthenticated
          is SessionStatus.RefreshFailure -> AuthState.Unauthenticated
        }
      }
    }
  }

  suspend fun login(email: String, password: String) {
    Supabase.client.auth.signInWith(Email) {
      this.email = email
      this.password = password
    }
  }

  suspend fun register(username: String, email: String, password: String) {
    Supabase.client.auth.signUpWith(Email) {
      this.email = email
      this.password = password
      this.data = buildJsonObject { put("username", username) }
    }
  }

  suspend fun logout() {
    Supabase.client.auth.signOut()
  }
}

