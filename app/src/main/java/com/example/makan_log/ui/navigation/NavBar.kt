package com.example.makan_log.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.makan_log.ui.theme.BrownMuted
import com.example.makan_log.ui.theme.Coral
import com.example.makan_log.ui.theme.CoralLight
import com.example.makan_log.ui.theme.White

private enum class Destination(
  val route: String,
  val label: String,
  val icon: ImageVector,
) {
  HOME(Route.Home, "Beranda", Icons.Default.Home),
  BOOKMARK(Route.Bookmark, "Simpan", Icons.Default.Bookmark),
  CREATE(Route.Create, "Buat", Icons.Default.Add),
  PROFILE(Route.Profile, "Profil", Icons.Default.AccountCircle),
}

@Composable
fun NavBar(navController: NavController) {
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(
        elevation = 10.dp,
      )
      .background(White)
      .navigationBarsPadding(),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .selectableGroup(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Destination.entries.forEach { item ->
        val isSelected = currentRoute == item.route
        val isCreate = item.route == Route.Create

        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
            .selectable(
              selected = isSelected,
              role = Role.Tab,
              indication = null,
              interactionSource = remember { MutableInteractionSource() },
              onClick = {
                if (currentRoute != item.route) {
                  navController.navigate(item.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                  }
                }
              },
            )
            .padding(top = 12.dp)
            .padding(horizontal = 12.dp),
        ) {
          if (isCreate) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Coral),
            ) {
              Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = White,
                modifier = Modifier.size(22.dp),
              )
            }
          } else {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) CoralLight else Color.Transparent),
            ) {
              Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = if (isSelected) Coral else BrownMuted,
                modifier = Modifier.size(22.dp),
              )
            }
          }
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = item.label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) Coral else BrownMuted,
          )
        }
      }
    }
  }
}