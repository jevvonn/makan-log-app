package com.example.makan_log.core.model

import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthSession {
  private val _user = MutableStateFlow<UserInfo?>(null)
  val user = _user.asStateFlow()

  fun update(newUser: UserInfo?) {
    _user.value = newUser
  }
}
