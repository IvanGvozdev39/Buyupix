package com.test.buyupix.presentation.screens.login_sections

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.test.buyupix.R
import com.test.buyupix.domain.formatter.PhoneNumberFormatterFactory
import com.test.buyupix.presentation.util.CountryBox
import com.test.buyupix.presentation.viewmodel.LoginViewModel
import com.test.buyupix.ui.theme.Black5
import com.test.buyupix.ui.theme.Gray90
import com.test.buyupix.ui.theme.Gray50
import com.test.buyupix.ui.theme.White100
import com.test.buyupix.ui.theme.robotoFamily

@Composable
fun EnterPhoneSection(viewModel: LoginViewModel = hiltViewModel()) {
    var inputText by remember {
        mutableStateOf(TextFieldValue(text = ""))
    }
    var inputNumber by remember {
        mutableStateOf("")
    }
    val selectedCountry by viewModel.selectedCountry.collectAsState()

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
                    val maxLength = selectedCountry.maxLengthNoCode

                    if (newText.text.length <= maxLength) {
                        inputNumber = newText.text.filter { it.isDigit() }

                        val formater = PhoneNumberFormatterFactory.createFormatter(
                            selectedCountry.code
                        )
                        val formattedInputNumber = formater.format(inputNumber)
                        inputText = TextFieldValue(
                            text = formattedInputNumber,
                            selection = TextRange(formattedInputNumber.length)
                        )
                    }
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
    }
}