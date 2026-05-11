package com.example.makan_log.feature.create

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.makan_log.ui.component.AppButton
import com.example.makan_log.ui.component.AppTextField
import com.example.makan_log.ui.component.BackButton
import com.example.makan_log.ui.component.PhotoPickerRow
import com.example.makan_log.ui.theme.BrownDark
import com.example.makan_log.ui.theme.BrownMuted
import com.example.makan_log.ui.theme.Coral
import com.example.makan_log.ui.theme.CoralLight
import com.example.makan_log.ui.theme.CoralSurface
import com.example.makan_log.ui.theme.White

@Composable
fun TopBarCreateScreen(
  onNavigateBack: () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      Modifier.padding(vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      BackButton(onClick = { onNavigateBack() })
      Spacer(Modifier.width(8.dp))
      Text(
        text = "Buat Post Baru",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
      )
    }
    Surface(
      color = CoralLight,
      contentColor = Coral,
      shape = RoundedCornerShape(12.dp)
    ) {
      Text(
        text = "Draft",
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
      )
    }
  }
}

@Composable
private fun MasakSendiriCard(
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = White,
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      // Emoji icon in soft background
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(CoralLight),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "👨‍🍳",
          fontSize = 22.sp,
        )
      }
      Spacer(Modifier.width(12.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = "Masak Sendiri?",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = BrownDark,
        )
        Text(
          text = "Tandai postingan resep buatanmu",
          fontSize = 12.sp,
          color = BrownMuted,
        )
      }
      Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
          checkedThumbColor = White,
          checkedTrackColor = Coral,
          uncheckedThumbColor = White,
          uncheckedTrackColor = CoralSurface,
        )
      )
    }
  }
}

@Composable
fun DialogSuccess(onConfirm: () -> Unit) {
  Dialog(
    onDismissRequest = {}
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
      shape = RoundedCornerShape(16.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 24.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Icon(
          imageVector = Icons.Filled.CheckCircle,
          contentDescription = "Berhasil Upload",
          tint = Coral,
          modifier = Modifier.size(64.dp)
        )

        Text(
          text = "Postinganmu Berhasil Diunggah!",
          modifier = Modifier.padding(24.dp),
          fontSize = 16.sp,
          textAlign = TextAlign.Center
        )
        Row(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          AppButton(
            text = "Okay",
            onClick = { onConfirm() },
            modifier = Modifier.padding(8.dp),
          )
        }
      }
    }
  }
}

@Composable
fun CreateScreen(
  onNavigateBack: () -> Unit,
  onNavigateToHome: () -> Unit,
  createViewModel: CreateViewModel = viewModel(),
) {
  var caption by rememberSaveable { mutableStateOf("") }
  var isHomeCook by rememberSaveable { mutableStateOf(false) }
  var restaurantName by rememberSaveable { mutableStateOf("") }
  var recipeLink by rememberSaveable { mutableStateOf("") }
  val images = remember { mutableStateListOf<Uri>() }

  if (createViewModel.isSuccess) {
    DialogSuccess(
      onConfirm = {
        onNavigateToHome()
      }
    )
  }

  Scaffold(
    topBar = { TopBarCreateScreen(onNavigateBack) },
    bottomBar = {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(androidx.compose.ui.graphics.Color.Transparent)
          .navigationBarsPadding()
          .padding(horizontal = 12.dp)
          .padding(bottom = 12.dp, top = 8.dp),
      ) {
        AppButton(
          text = "Posting Sekarang",
          leadingIcon = Icons.AutoMirrored.Rounded.Send,
          enabled = !createViewModel.isLoading,
          onClick = {
            createViewModel.createPost(
              imageUris = images,
              caption,
              restaurantName,
              isSelfMade = isHomeCook,
              recipeLink,
              onSuccess = {
                caption = ""
                isHomeCook = false
                restaurantName = ""
                recipeLink = ""
                images.clear()


              }
            )
          },
        )
      }
    },
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 12.dp),
    containerColor = androidx.compose.ui.graphics.Color.Transparent,
  ) { paddingValues ->


    val opsionalLabel = buildAnnotatedString {
      append("Nama Restoran ")
      withStyle(SpanStyle(color = BrownMuted, fontWeight = FontWeight.Normal)) {
        append("(opsional)")
      }
    }

    val opsionalRecipeLabel = buildAnnotatedString {
      append("Link Resep ")
      withStyle(SpanStyle(color = BrownMuted, fontWeight = FontWeight.Normal)) {
        append("(opsional)")
      }
    }

    Column(
      modifier = Modifier
        .padding(paddingValues)
        .verticalScroll(rememberScrollState())
    ) {
      PhotoPickerRow(
        images = images,
        onImagesAdded = { uris -> images.addAll(uris) },
        onImageRemoved = { uri -> images.remove(uri) },
      )

      Spacer(Modifier.height(20.dp))

      AppTextField(
        value = caption,
        onValueChange = { if (it.length <= createViewModel.CAPTION_MAX_LENGTH) caption = it },
        label = "Caption",
        placeholder = "Nasi goreng terenak banget, bumbunya meresap...",
        singleLine = false,
        enabled = !createViewModel.isLoading,
        minHeight = 120.dp,
      )
      Text(
        text = "${caption.length} / ${createViewModel.CAPTION_MAX_LENGTH}",
        fontSize = 12.sp,
        color = BrownMuted,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 4.dp, end = 2.dp),
        textAlign = TextAlign.End,
      )

      Spacer(Modifier.height(16.dp))

      MasakSendiriCard(
        checked = isHomeCook,
        onCheckedChange = { isHomeCook = it },
      )

      Spacer(Modifier.height(20.dp))

      AppTextField(
        value = restaurantName,
        onValueChange = { restaurantName = it },
        label = "Nama Restoran",
        labelAnnotated = opsionalLabel,
        enabled = !createViewModel.isLoading,
        placeholder = "Nasi Goreng Kebon Sirih",
      )
      Text(
        text = "Kosongkan jika ini adalah masakanmu sendiri.",
        fontSize = 12.sp,
        color = BrownMuted,
        modifier = Modifier.padding(top = 6.dp),
      )

      Spacer(Modifier.height(20.dp))

      AppTextField(
        value = recipeLink,
        onValueChange = { recipeLink = it },
        label = "Link Resep",
        enabled = !createViewModel.isLoading,
        labelAnnotated = opsionalRecipeLabel,
        placeholder = "https://...",
        keyboardType = KeyboardType.Uri,
      )

      Spacer(Modifier.height(28.dp))
    }
  }
}