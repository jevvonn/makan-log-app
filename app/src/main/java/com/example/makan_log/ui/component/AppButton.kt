package com.example.makan_log.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.theme.Coral
import com.example.makan_log.ui.theme.White

@Composable
fun AppButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  leadingIcon: ImageVector? = null,
) {
  Button(
    onClick = onClick,
    enabled = enabled,
    shape = RoundedCornerShape(100.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = Coral,
      contentColor = White,
    ),
    modifier = modifier
      .fillMaxWidth()
      .height(52.dp),
  ) {
    if (leadingIcon != null) {
      Icon(
        imageVector = leadingIcon,
        contentDescription = null,
        modifier = Modifier.size(18.dp),
      )
      Spacer(modifier = Modifier.width(8.dp))
    }
    Text(
      text = text,
      fontSize = 15.sp,
      fontWeight = FontWeight.SemiBold,
    )
  }
}

