package com.ivangvozdev.buyupix.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivangvozdev.buyupix.domain.enumeration.LoginStage
import com.ivangvozdev.buyupix.presentation.screens.login_sections.ConfirmCodeSection
import com.ivangvozdev.buyupix.presentation.screens.login_sections.EnterPhoneSection
import com.ivangvozdev.buyupix.presentation.screens.login_sections.SelectCountrySection
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val currentStep by viewModel.currentStep.collectAsState()
    when(currentStep) {
        LoginStage.ENTER_PHONE -> EnterPhoneSection()
        LoginStage.SELECT_COUNTRY -> SelectCountrySection()
        LoginStage.CONFIRM_CODE -> ConfirmCodeSection()
    }
}