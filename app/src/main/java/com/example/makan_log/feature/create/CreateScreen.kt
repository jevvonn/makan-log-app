package com.example.makan_log.feature.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CreateScreen(
  onNavigateBack: () -> Unit
) {
  Scaffold(
    Modifier.fillMaxWidth()
  ) { paddingValues ->

    Column(
      Modifier.padding(paddingValues)
    ) {
      Button(
        onClick = { onNavigateBack() }
      ) {
        Text("Back")
      }
      
      Text("Create Page")
    }
  }
}