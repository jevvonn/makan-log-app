package com.example.makan_log.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

private val LightColorScheme = lightColorScheme(
  primary = Coral,
  onPrimary = White,
  primaryContainer = CoralLight,
  onPrimaryContainer = BrownDark,
  secondary = BrownMid,
  onSecondary = White,
  background = Cream,
  onBackground = BrownDark,
  surface = White,
  onSurface = BrownDark,
  onSurfaceVariant = BrownMuted,
  outline = CoralSurface,
)

@Composable
fun MakanlogTheme(
  content: @Composable () -> Unit
) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
    }
  }

  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    content = content
  )
}