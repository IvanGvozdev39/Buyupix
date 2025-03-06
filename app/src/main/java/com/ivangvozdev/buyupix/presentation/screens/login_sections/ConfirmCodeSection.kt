package com.ivangvozdev.buyupix.presentation.screens.login_sections

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivangvozdev.buyupix.R
import com.ivangvozdev.buyupix.presentation.navigation.Screen
import com.ivangvozdev.buyupix.presentation.util.BackButton
import com.ivangvozdev.buyupix.presentation.util.CustomToast
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel
import com.ivangvozdev.buyupix.ui.theme.Black5
import com.ivangvozdev.buyupix.ui.theme.Blue90
import com.ivangvozdev.buyupix.ui.theme.Gray50
import com.ivangvozdev.buyupix.ui.theme.Gray85
import com.ivangvozdev.buyupix.ui.theme.Gray90
import com.ivangvozdev.buyupix.ui.theme.White100
import com.ivangvozdev.buyupix.ui.theme.robotoFamily
import kotlinx.coroutines.delay

@Composable
fun ConfirmCodeSection(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    var inputCode by remember {
        mutableStateOf("")
    }
    var cursorPosition by remember {
        mutableIntStateOf(0)
    }

    val remainingTimeSecs by viewModel.remainingTimeSecs.collectAsState()
    val timerActive by viewModel.timerActive.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var loadingIndex by remember { mutableIntStateOf(0) }

    var showErrorToast by remember { mutableStateOf(false) }
    var errorToastMessage by remember { mutableStateOf("") }

    val activity = LocalActivity.current as? ComponentActivity

    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(Unit) {
        if (!timerActive)
            viewModel.restartCodeResendTimer()
    }

    LaunchedEffect(isLoading) {
        while (isLoading) {
            repeat((0 until 6).count()) {
                delay(200L)
                loadingIndex++
            }
            loadingIndex = 0
        }
    }

    LaunchedEffect(showErrorToast) {
            delay(5000)
            showErrorToast = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 16.dp, start = 10.dp, end = 10.dp)
            .background(White100)
    ) {
        BackButton(boxSize = 24.dp, backgroundColor = White100, onClick = {
            viewModel.moveToEnterPhone()
        })
        Text(
            text = stringResource(id = R.string.code),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Black5,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 18.dp, start = 6.dp, end = 6.dp)
        )
        Text(
            text = stringResource(id = R.string.enter_the_code_you_received),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Normal,
            color = Gray50,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp, start = 6.dp, end = 6.dp)
        )
        BasicTextField(
            value = inputCode,
            enabled = !isLoading,
            onValueChange = {
                if (it.length <= 6 && it.isDigitsOnly()) {
                    inputCode = it
                    cursorPosition = it.length

                    if (it.length == 6) {
                        keyboardController?.hide()
                        isLoading = true

                        viewModel.verifyCode(
                            code = inputCode,
                            onSuccess = {
                                isLoading = false
                                navController.navigate(route = Screen.NoSubscriptionsScreen.route)
                            },
                            onError = { message ->
                                errorToastMessage = message
                                showErrorToast = true
                                isLoading = false
                                inputCode = ""
                                cursorPosition = 0
                            }
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            decorationBox = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(6) { index ->
                            val number = when {
                                index >= inputCode.length -> ""
                                else -> inputCode[index]
                            }

                            val backgroundColor = when {
                                isLoading && index == loadingIndex -> Gray85 // Loading-like animation
                                isLoading -> Gray90
                                index == cursorPosition -> Gray85
                                else -> Gray90
                            }

                            Box(
                                modifier = Modifier
                                    .height(44.dp)
                                    .width(36.dp)
                                    .background(backgroundColor, shape = RoundedCornerShape(10.dp))
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Text(
                                    text = number.toString(),
                                    fontSize = 18.sp,
                                    fontFamily = robotoFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = if (!isLoading) Black5 else  Gray50
                                )
                            }
                        }
                    }
                }
            }
        )
        val resendText: String
        val resendColor: Color
        val resendFontWeight: FontWeight

        if (remainingTimeSecs > 0) {
            resendText = stringResource(id = R.string.resend_the_code_in) + " 0:${remainingTimeSecs.toString().padStart(2, '0')}"
            resendColor = Black5
            resendFontWeight = FontWeight.Normal
        } else {
            resendText = stringResource(id = R.string.no_code_received)
            resendColor = Blue90
            resendFontWeight = FontWeight.Medium
        }

        Text(
            text = resendText,
            color = resendColor,
            fontFamily = robotoFamily,
            fontWeight = resendFontWeight,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(
                    enabled = !timerActive,
                    onClick = {
                        if (activity != null) {
                            viewModel.restartCodeResendTimer()
                            viewModel.sendVerificationCode(
                                phoneNumber = viewModel.selectedCountry.value.phoneCode + viewModel.inputNumber.value,
                                activity = activity
                            )
                        }
                    }
                )
        )
        
        if (showErrorToast) 
            CustomToast(message = errorToastMessage, duration = 4000)
    }
}