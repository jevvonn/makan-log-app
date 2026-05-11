package com.example.makan_log.core.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostImageDTO(
  @SerialName("post_id")
  val postId: String,

  @SerialName("image_url")
  val imageURL: String,

  @SerialName("display_order")
  val displayOrder: Int
)

@Serializable
data class PostDTO(
  @SerialName("caption")
  val caption: String,

  @SerialName("restaurant_name")
  val restaurantName: String?,

  @SerialName("is_self_made")
  val isSelfMade: Boolean,

  @SerialName("recipe_link")
  val recipeLink: String?,

  @SerialName("user_id")
  val userId: String?
)