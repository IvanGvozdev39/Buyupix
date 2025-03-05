package com.ivangvozdev.buyupix.presentation.screens.login_sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel
import com.ivangvozdev.buyupix.ui.theme.White100

@Composable
fun ConfirmCodeSection(viewModel: LoginViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize().background(White100))
}