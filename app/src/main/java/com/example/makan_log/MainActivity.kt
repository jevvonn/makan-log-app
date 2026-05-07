package com.example.makan_log

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.makan_log.navigation.AppNavGraph
import com.example.makan_log.ui.theme.MakanlogTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MakanlogTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          AppNavGraph()
        }
      }
    }
  }
}
