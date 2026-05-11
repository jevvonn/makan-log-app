package com.example.makan_log.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
  @SerialName("id")
  val id: String,

  @SerialName("caption")
  val caption: String,

  @SerialName("restaurant_name")
  val restaurantName: String?,

  @SerialName("is_self_made")
  val isSelfMade: Boolean,

  @SerialName("recipe_link")
  val recipeLink: String?,

  @SerialName("user_id")
  val userId: String
)