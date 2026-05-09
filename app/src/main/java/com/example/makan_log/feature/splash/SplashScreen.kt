package com.example.makan_log.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.theme.BrownMid
import com.example.makan_log.ui.theme.Coral
import com.example.makan_log.ui.theme.White

@Composable
@Preview
fun SplashScreen() {
  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .size(80.dp)
        .background(Coral, RoundedCornerShape(22.dp)),
    ) {
      Icon(
        imageVector = Icons.Filled.Restaurant,
        contentDescription = "MakanLog",
        tint = White,
        modifier = Modifier.size(40.dp),
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "MakanLog",
      fontSize = 34.sp,
      fontWeight = FontWeight.ExtraBold,
      color = Coral,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = "Catat & bagikan petualangan kulinermu",
      fontSize = 14.sp,
      color = BrownMid,
    )
  }
}