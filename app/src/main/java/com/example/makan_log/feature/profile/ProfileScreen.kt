package com.example.makan_log.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
  Scaffold(
    Modifier.fillMaxWidth()
  ) { paddingValues ->
    Column(
      Modifier.padding(paddingValues)
    ) {
      Text("Profile Page")
    }
  }
}