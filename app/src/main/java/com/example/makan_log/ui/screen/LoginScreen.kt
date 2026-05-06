package com.example.makan_log.ui.screen

import androidx.compose.runtime.Composable

/**
 * Kept as a stub. Login UI has been merged into [AuthScreen] with a tab switcher.
 * Navigate to the "auth/login" route instead.
 */
@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    AuthScreen(startOnLogin = true)
}
