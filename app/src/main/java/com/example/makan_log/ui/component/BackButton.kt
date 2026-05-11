package com.example.makan_log.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.makan_log.ui.theme.White

@Preview
@Composable
fun BackButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
) {
  FilledIconButton(
    onClick = onClick,
    shape = RoundedCornerShape(12.dp),
    colors = IconButtonDefaults.filledIconButtonColors(
      containerColor = White,
      contentColor = Color.Black,
    ),
    modifier = modifier
  ) {
    Icon(
      imageVector = Icons.Filled.ChevronLeft,
      contentDescription = "Back",
    )
  }
}
