package com.test.buyupix.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.buyupix.domain.LoginStage
import com.test.buyupix.presentation.screens.login_sections.EnterPhoneSection
import com.test.buyupix.presentation.screens.login_sections.SelectCountrySection
import com.test.buyupix.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val currentStep by viewModel.currentStep.collectAsState()
    when(currentStep) {
        LoginStage.ENTER_PHONE -> EnterPhoneSection()
        LoginStage.SELECT_COUNTRY -> SelectCountrySection()
        LoginStage.CONFIRM_CODE -> TODO()
    }
}