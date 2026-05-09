package com.example.makan_log.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class AlertType {
  Info, Success, Warning, Danger
}

private data class AlertStyle(
  val background: Color,
  val contentColor: Color,
  val icon: ImageVector,
  val iconDescription: String,
)

private fun styleFor(type: AlertType): AlertStyle = when (type) {
  AlertType.Info -> AlertStyle(
    background = Color(0xFFDBEAFD),
    contentColor = Color(0xFF1E4D8C),
    icon = Icons.Rounded.Info,
    iconDescription = "Info",
  )

  AlertType.Success -> AlertStyle(
    background = Color(0xFFD6EDDA),
    contentColor = Color(0xFF1A5C34),
    icon = Icons.Rounded.CheckCircle,
    iconDescription = "Success",
  )

  AlertType.Warning -> AlertStyle(
    background = Color(0xFFFDF6D3),
    contentColor = Color(0xFF7A5C00),
    icon = Icons.Rounded.Warning,
    iconDescription = "Warning",
  )

  AlertType.Danger -> AlertStyle(
    background = Color(0xFFFBD7D7),
    contentColor = Color(0xFF8B1A1A),
    icon = Icons.Rounded.Warning,
    iconDescription = "Danger",
  )
}

@Composable
fun AppAlert(
  message: String,
  type: AlertType,
  modifier: Modifier = Modifier,
) {
  val style = styleFor(type)

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(style.background)
      .padding(horizontal = 16.dp, vertical = 14.dp),
  ) {
    Icon(
      imageVector = style.icon,
      contentDescription = style.iconDescription,
      tint = style.contentColor,
    )
    Text(
      text = message,
      color = style.contentColor,
      fontSize = 14.sp,
      fontWeight = FontWeight.Normal,
      lineHeight = 20.sp,
    )
  }
}


