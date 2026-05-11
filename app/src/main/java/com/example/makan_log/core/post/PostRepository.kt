package com.example.makan_log.core.post

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.example.makan_log.core.domain.dto.PostDTO
import com.example.makan_log.core.domain.dto.PostImageDTO
import com.example.makan_log.core.domain.model.Post
import com.example.makan_log.core.network.Supabase
import com.example.makan_log.core.util.BuildSupabaseImagePublicURL
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.upload
import io.ktor.http.ContentType
import java.util.UUID


object PostRepository {

  private suspend fun uploadImage(uri: Uri): String {
    val imageUrl = Supabase.client.storage.from("posts").upload(
      path = UUID.randomUUID().toString(),
      uri = uri,
      options = {
        upsert = true
        contentType = ContentType.Image.JPEG
      }
    )

    return BuildSupabaseImagePublicURL("posts", imageUrl.path)
  }

  private suspend fun createPostImage(postId: String, publicUrl: String, displayOrder: Int) {
    val postImage = PostImageDTO(
      postId,
      imageURL = publicUrl,
      displayOrder,
    )

    Supabase.client.postgrest.from("post_images").insert(postImage)
  }

  suspend fun createPost(imageUris: List<Uri>, post: PostDTO) {
    val publicUrls = mutableStateListOf<String>()

    imageUris.forEach { uri ->
      val publicUrl = uploadImage(uri)
      publicUrls.add(publicUrl)
    }

    val result = Supabase.client.postgrest.from("posts").insert(post) {
      select()
    }.decodeSingle<Post>()

    publicUrls.forEachIndexed { index, publicUrl ->
      createPostImage(result.id, publicUrl, (index + 1))
    }
  }
}