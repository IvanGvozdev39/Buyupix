package com.ivangvozdev.buyupix.presentation.screens.login_sections

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivangvozdev.buyupix.R
import com.ivangvozdev.buyupix.presentation.util.CountryBox
import com.ivangvozdev.buyupix.presentation.util.CustomToast
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel
import com.ivangvozdev.buyupix.ui.theme.Black5
import com.ivangvozdev.buyupix.ui.theme.Blue90
import com.ivangvozdev.buyupix.ui.theme.Gray50
import com.ivangvozdev.buyupix.ui.theme.Gray90
import com.ivangvozdev.buyupix.ui.theme.White100
import com.ivangvozdev.buyupix.ui.theme.robotoFamily
import kotlinx.coroutines.delay

@Composable
fun EnterPhoneSection(viewModel: LoginViewModel = hiltViewModel()) {
    val inputTextString by viewModel.inputTextString.collectAsState()
    var inputText by remember {
        mutableStateOf(TextFieldValue(text = inputTextString))
    }
    val inputNumber by viewModel.inputNumber.collectAsState()

    val selectedCountry by viewModel.selectedCountry.collectAsState()

    val continueButtonEnabled by viewModel.continueButtonEnabled.collectAsState()

    var showVerificationFailedToast by remember {
        mutableStateOf(false)
    }

    val activity = LocalActivity.current as? ComponentActivity
    val toastMessage by viewModel.toastMessage.collectAsState()

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            showVerificationFailedToast = true
            delay(7000)
            viewModel.clearToastMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White100)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.lets_get_started),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Black5,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.enter_phone),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Normal,
            color = Gray50,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
        ) {

            CountryBox(selectedCountry, onClick = {
                viewModel.moveToSelectCountry()
            })

            Spacer(modifier = Modifier.width(10.dp))
            BasicTextField(
                value = inputText,
                onValueChange = { newText ->
                    val formattedText = viewModel.onPhoneInputChanged(newText.text)
                    inputText = TextFieldValue(text = formattedText, selection = TextRange(formattedText.length))

                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    color = Black5
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Gray90, RoundedCornerShape(10.dp))
                    .padding(14.dp),
                decorationBox = { innerTextField ->
                    if (inputText.text.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.mobile_number),
                            fontSize = 16.sp,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Normal,
                            color = Gray50
                        )
                    }
                    innerTextField()
                }
            )
        }
        Button(
            onClick = {
                if (activity != null) {
                    viewModel.sendVerificationCode(
                        phoneNumber = selectedCountry.phoneCode + inputNumber,
                        activity = activity
                    )
                }
            },
            enabled = continueButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue90,
                contentColor = White100
            )
        ) {
            Text(
                text = stringResource(id = R.string.continue_btn),
                fontSize = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        if (showVerificationFailedToast)
            CustomToast(message = stringResource(id = R.string.verification_failed) + toastMessage)

    }
}