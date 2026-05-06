package com.example.makan_log.ui.screen

import androidx.compose.runtime.Composable

/**
 * Kept as a stub. Register UI has been merged into [AuthScreen] with a tab switcher.
 * Navigate to the "auth/register" route instead.
 */
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    AuthScreen(startOnLogin = false)
}
