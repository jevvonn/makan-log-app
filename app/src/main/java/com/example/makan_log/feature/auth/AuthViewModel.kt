package com.example.makan_log.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.auth.AuthRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.makan_log.core.util.GetMessageFromHttpResponse
import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.exceptions.UnknownRestException

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
    onError: (Exception) -> Unit = {},
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
        setErrorMessage(e.errorCode)
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
      errorMessage = null
      isLoading = true

      try {
        AuthRepository.register(username, email, password)
        println("Register success")
        onSuccess()
      } catch (e: Exception) {
        println("Register failed: ${e.message}")

        if (e is AuthRestException) {
          setErrorMessage(e.errorCode)
        }

        if (e is UnknownRestException) {
          setErrorMessage(customMessage = GetMessageFromHttpResponse(e.response))
        }

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
      errorMessage = null
      isLoading = true

      try {
        AuthRepository.logout()
        println("Logout success")
        onSuccess()
      } catch (e: AuthRestException) {
        println("Logout failed: ${e.message}")
        setErrorMessage(e.errorCode)
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }

  private fun setErrorMessage(errorCode: AuthErrorCode? = null, customMessage: String? = null) {
    if (customMessage != null) {
      errorMessage = customMessage
      return
    }

    errorMessage = when (errorCode) {
      AuthErrorCode.InvalidCredentials -> ErrorMessage.LOGIN_FAILED
      AuthErrorCode.UserAlreadyExists -> ErrorMessage.EMAIL_USED
      AuthErrorCode.WeakPassword -> ErrorMessage.WEAK_PASSWORD
      else -> ErrorMessage.UNKNOWN_ERROR
    }
  }
}

