package com.example.makan_log.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makan_log.ui.components.*
import com.example.makan_log.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.makan_log.ui.viewmodel.AuthScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    startOnLogin: Boolean = true,
    authViewModel: AuthScreenViewModel =  viewModel(),
    onNavigateToHome: () -> Unit,
) {
    var isLoginTab by rememberSaveable { mutableStateOf(startOnLogin) }

    var loginEmail    by rememberSaveable { mutableStateOf("") }
    var loginPassword by rememberSaveable { mutableStateOf("") }

    var regUsername by rememberSaveable { mutableStateOf("") }
    var regEmail    by rememberSaveable { mutableStateOf("") }
    var regPassword by rememberSaveable { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Cream),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 64.dp, bottom = 40.dp),
            ) {
                AuthHeader()

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                    ) {
                        AuthTabRow(
                            isLoginSelected = isLoginTab,
                            onLoginClick    = { isLoginTab = true },
                            onRegisterClick = { isLoginTab = false },
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        if (isLoginTab) {
                            AppTextField(
                                value         = loginEmail,
                                onValueChange = { loginEmail = it },
                                label         = "Email",
                                placeholder   = "email@contoh.com",
                                keyboardType  = KeyboardType.Email,
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            AppTextField(
                                value         = loginPassword,
                                onValueChange = { loginPassword = it },
                                label         = "Password",
                                placeholder   = "••••••••",
                                isPassword    = true,
                            )

                            Box(
                                contentAlignment = Alignment.CenterEnd,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                TextButton(onClick = { /* TODO: forgot password */ }) {
                                    Text(
                                        text       = "Lupa password?",
                                        fontSize   = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color      = Coral,
                                    )
                                }
                            }
                        } else {
                            AppTextField(
                                value         = regUsername,
                                onValueChange = { regUsername = it },
                                label         = "Username",
                                placeholder   = "username",
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            AppTextField(
                                value         = regEmail,
                                onValueChange = { regEmail = it },
                                label         = "Email",
                                placeholder   = "email@contoh.com",
                                keyboardType  = KeyboardType.Email,
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            AppTextField(
                                value         = regPassword,
                                onValueChange = { regPassword = it },
                                label         = "Password",
                                placeholder   = "••••••••",
                                isPassword    = true,
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        AppButton(
                            text    = if (isLoginTab) "Masuk" else "Daftar",
                            onClick = {
                                if (isLoginTab) {
                                    authViewModel.login(
                                        email = loginEmail,
                                        password = loginPassword,
                                        onSuccess = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Login Berhasil! Selamat datang kembali.",
                                                    duration = SnackbarDuration.Long,
                                                    withDismissAction = true
                                                )
                                            }
                                            onNavigateToHome()
                                        }
                                    )
                                } else {
                                    authViewModel.register(
                                        username = regUsername,
                                        email = regEmail,
                                        password = regPassword,
                                        onSuccess = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Register Berhasil! Silakan masuk dengan akun baru Anda.",
                                                    duration = SnackbarDuration.Short,
                                                    withDismissAction = true
                                                )
                                            }
                                            isLoginTab = true
                                        }
                                    )
                                }
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                TextButton(
                    onClick = { isLoginTab = !isLoginTab },
                ) {
                    Text(
                        text = buildAnnotatedString {
                            if (isLoginTab) {
                                withStyle(SpanStyle(color = BrownMid, fontWeight = FontWeight.Normal)) {
                                    append("Belum punya akun? ")
                                }
                                withStyle(SpanStyle(color = Coral, fontWeight = FontWeight.SemiBold)) {
                                    append("Daftar di sini")
                                }
                            } else {
                                withStyle(SpanStyle(color = BrownMid, fontWeight = FontWeight.Normal)) {
                                    append("Sudah punya akun? ")
                                }
                                withStyle(SpanStyle(color = Coral, fontWeight = FontWeight.SemiBold)) {
                                    append("Masuk di sini")
                                }
                            }
                        },
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

/**
 * Pill-style tab row with active coral highlight.
 * Reusable for any two-tab switcher in the app.
 */
@Composable
private fun AuthTabRow(
    isLoginSelected: Boolean,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(CoralLight)
            .padding(4.dp),
    ) {
        Row {
            AuthTabItem(
                text       = "Masuk",
                selected   = isLoginSelected,
                onClick    = onLoginClick,
                modifier   = Modifier.weight(1f),
            )
            AuthTabItem(
                text       = "Daftar",
                selected   = !isLoginSelected,
                onClick    = onRegisterClick,
                modifier   = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun AuthTabItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(if (selected) Coral else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
    ) {
        Text(
            text       = text,
            fontSize   = 15.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color      = if (selected) White else BrownDark,
        )
    }
}

