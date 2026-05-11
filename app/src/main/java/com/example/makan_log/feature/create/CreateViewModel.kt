package com.example.makan_log.feature.create

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makan_log.core.auth.AuthRepository
import com.example.makan_log.core.domain.dto.PostDTO
import com.example.makan_log.core.post.PostRepository
import io.github.jan.supabase.auth.exception.AuthRestException
import kotlinx.coroutines.launch

class CreateViewModel : ViewModel() {
  val CAPTION_MAX_LENGTH = 280
  var isLoading by mutableStateOf(false)
    private set
  var isSuccess by mutableStateOf(false)
    private set

  fun createPost(
    imageUris: List<Uri>,
    caption: String,
    restaurantName: String,
    isSelfMade: Boolean,
    recipeLink: String,
    onSuccess: () -> Unit = {},
    onError: (Exception) -> Unit = {},
  ) {
    viewModelScope.launch {
      isLoading = true
      val post = PostDTO(
        caption,
        restaurantName,
        isSelfMade,
        recipeLink,
        userId = AuthRepository.user?.id
      )

      try {
        PostRepository.createPost(imageUris, post)
        println("Post success")
        isSuccess = true
        onSuccess()
      } catch (e: AuthRestException) {
        println("Post failed: ${e.message}")
        onError(e)
      } finally {
        isLoading = false
      }
    }
  }
}

