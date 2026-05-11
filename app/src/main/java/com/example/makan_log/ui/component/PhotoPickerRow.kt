package com.example.makan_log.ui.component

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.theme.BrownDark
import com.example.makan_log.ui.theme.Coral
import com.example.makan_log.ui.theme.CoralLight
import com.example.makan_log.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
private fun rememberBitmapFromUri(uri: Uri): ImageBitmap? {
  val context = LocalContext.current
  val bitmap by produceState<ImageBitmap?>(initialValue = null, key1 = uri) {
    value = withContext(Dispatchers.IO) {
      try {
        context.contentResolver.openInputStream(uri)?.use { stream ->
          BitmapFactory.decodeStream(stream)?.asImageBitmap()
        }
      } catch (_: Exception) {
        null
      }
    }
  }
  return bitmap
}

@Composable
fun PhotoPickerRow(
  images: List<Uri>,
  onImagesAdded: (List<Uri>) -> Unit,
  onImageRemoved: (Uri) -> Unit,
  modifier: Modifier = Modifier,
) {
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetMultipleContents()
  ) { uris ->
    if (uris.isNotEmpty()) onImagesAdded(uris)
  }

  Column(modifier = modifier) {
    Text(
      text = "Foto Makanan",
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = BrownDark,
    )
    Spacer(Modifier.height(10.dp))
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      item {
        // Add photo button
        val dashColor = Coral
        Box(
          modifier = Modifier
            .size(130.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(CoralLight)
            .drawBehind {
              drawRoundRect(
                color = dashColor,
                style = Stroke(
                  width = 2.dp.toPx(),
                  pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(14f, 7f), 0f
                  )
                ),
                cornerRadius = CornerRadius(14.dp.toPx())
              )
            }
            .clickable { launcher.launch("image/*") },
          contentAlignment = Alignment.Center
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = Icons.Rounded.AddPhotoAlternate,
              contentDescription = "Tambah Foto",
              tint = Coral,
              modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
              text = if (images.isEmpty()) "Tambah Foto" else "Tambah",
              color = Coral,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
            )
          }
        }
      }

      items(images) { uri ->
        Box(
          modifier = Modifier
            .size(130.dp)
            .clip(RoundedCornerShape(14.dp))
        ) {
          val bitmap = rememberBitmapFromUri(uri)
          if (bitmap != null) {
            Image(
              bitmap = bitmap,
              contentDescription = null,
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
          } else {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(CoralLight)
            )
          }
          // Remove button
          Box(
            modifier = Modifier
              .align(Alignment.TopEnd)
              .padding(6.dp)
              .size(22.dp)
              .clip(CircleShape)
              .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.55f))
              .clickable { onImageRemoved(uri) },
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Rounded.Close,
              contentDescription = "Hapus foto",
              tint = White,
              modifier = Modifier.size(13.dp)
            )
          }
        }
      }
    }
  }
}

