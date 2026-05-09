package com.example.makan_log.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.auth.AuthRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException

data object ErrorMessage {
  const val LOGIN_FAILED = "Email atau password salah. Silakan coba lagi."
  const val EMAIL_USED =
    "Email sudah digunakan. Silakan gunakan email lain atau masuk dengan akun yang sudah ada."
  const val WEAK_PASSWORD =
    "Password terlalu lemah. Gunakan minimal 8 karakter dengan huruf besar, kecil, angka, dan simbol."
  const val UNKNOWN_ERROR = "Terjadi kesalahan. Silakan coba lagi nanti."
}

class AuthViewModel : ViewModel() {

  var isLoading by mutableStateOf(false)
    private set
  var errorMessage by mutableStateOf<String?>(null)

  fun login(
    email: String,
    password: String,
    onSuccess: () -> Unit = {},
    onError: (AuthRestException) -> Unit = {},
  ) {
    viewModelScope.launch {
      errorMessage = null
      isLoading = true

      try {
        AuthRepository.login(email, password)
        println("Login success")
        onSuccess()
      } catch (e: AuthRestException) {
        println("Login failed: ${e.message}")
        setErrorMessage(e)
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
    onError: (AuthRestException) -> Unit = {},
  ) {
    viewModelScope.launch {
      errorMessage = null
      isLoading = true

      try {
        AuthRepository.register(username, email, password)
        println("Register success")
        onSuccess()
      } catch (e: AuthRestException) {
        println("Register failed: ${e.message}")
        setErrorMessage(e)
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }

  fun logout(
    onSuccess: () -> Unit = {},
    onError: (AuthRestException) -> Unit = {},
  ) {
    viewModelScope.launch {
      errorMessage = null
      isLoading = true

      try {
        AuthRepository.logout()
        println("Logout success")
        onSuccess()
      } catch (e: AuthRestException) {
        println("Logout failed: ${e.message}")
        setErrorMessage(e)
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }

  private fun setErrorMessage(exception: AuthRestException) {
    errorMessage = when (exception.errorCode) {
      AuthErrorCode.InvalidCredentials -> ErrorMessage.LOGIN_FAILED
      AuthErrorCode.UserAlreadyExists -> ErrorMessage.EMAIL_USED
      AuthErrorCode.WeakPassword -> ErrorMessage.WEAK_PASSWORD
      else -> ErrorMessage.UNKNOWN_ERROR
    }
  }
}

