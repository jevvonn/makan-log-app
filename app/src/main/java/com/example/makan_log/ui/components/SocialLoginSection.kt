package com.example.makan_log.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.theme.BrownDark
import com.example.makan_log.ui.theme.BrownMuted
import com.example.makan_log.ui.theme.CoralSurface
import com.example.makan_log.ui.theme.White

@Composable
fun SocialLoginSection(
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CoralSurface,
            )
            Text(
                text = "  atau lanjut dengan  ",
                fontSize = 13.sp,
                color = BrownMuted,
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CoralSurface,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            SocialButton(
                label = "Google",
                onClick = onGoogleClick,
                modifier = Modifier.weight(1f),
            )
            SocialButton(
                label = "Apple",
                onClick = onAppleClick,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun SocialButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.5.dp, CoralSurface),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = White,
            contentColor   = BrownDark,
        ),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier.height(48.dp),
    ) {
        Text(
            text       = label,
            fontSize   = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

