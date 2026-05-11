package com.example.makan_log.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.theme.*

@Composable
fun AppTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  modifier: Modifier = Modifier,
  placeholder: String = "",
  isPassword: Boolean = false,
  keyboardType: KeyboardType = KeyboardType.Text,
  singleLine: Boolean = true,
  minHeight: Dp? = null,
  labelAnnotated: AnnotatedString? = null,
  enabled: Boolean = true,
) {
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val visualTransformation = when {
    isPassword && !passwordVisible -> PasswordVisualTransformation()
    else -> VisualTransformation.None
  }

  Column(modifier = modifier.fillMaxWidth()) {
    if (labelAnnotated != null) {
      Text(
        text = labelAnnotated,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = BrownDark,
      )
    } else {
      Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = BrownDark,
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    BasicTextField(
      value = value,
      onValueChange = onValueChange,
      singleLine = singleLine,
      enabled = enabled,
      visualTransformation = visualTransformation,
      keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
      textStyle = TextStyle(
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        color = BrownDark,
        fontSize = 15.sp,
      ),
      decorationBox = { innerTextField ->
        Row(
          verticalAlignment = if (minHeight != null) Alignment.Top else Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
            .background(CoralLight, RoundedCornerShape(14.dp))
            .heightIn(min = minHeight ?: 56.dp)
            .padding(horizontal = 16.dp)
            .padding(vertical = if (minHeight != null) 15.dp else 0.dp)
        ) {
          Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart,
          ) {
            if (value.isEmpty()) {
              Text(
                text = placeholder,
                color = BrownMuted,
                fontSize = 15.sp,
              )
            }
            innerTextField()
          }
          if (isPassword) {
            IconButton(
              onClick = { passwordVisible = !passwordVisible },
              modifier = Modifier.size(24.dp),
            ) {
              Icon(
                imageVector = if (passwordVisible)
                  Icons.Outlined.Visibility
                else
                  Icons.Outlined.VisibilityOff,
                contentDescription = if (passwordVisible) "Sembunyikan password" else "Tampilkan password",
                tint = BrownMuted,
                modifier = Modifier.size(20.dp),
              )
            }
          }
        }
      },
    )
  }
}

