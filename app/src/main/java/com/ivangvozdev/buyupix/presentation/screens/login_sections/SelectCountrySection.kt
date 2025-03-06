package com.ivangvozdev.buyupix.presentation.screens.login_sections

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivangvozdev.buyupix.R
import com.ivangvozdev.buyupix.presentation.util.BackButton
import com.ivangvozdev.buyupix.presentation.util.animaton.useSlideInAnimation
import com.ivangvozdev.buyupix.presentation.util.country_list.CountryList
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel
import com.ivangvozdev.buyupix.ui.theme.Gray90
import com.ivangvozdev.buyupix.ui.theme.White100
import com.ivangvozdev.buyupix.ui.theme.robotoFamily
import kotlin.math.roundToInt

@Composable
fun SelectCountrySection(viewModel: LoginViewModel = hiltViewModel()) {
    BackHandler {
        viewModel.moveToEnterPhone()
    }

    val offsetX = useSlideInAnimation(initialOffset = -40f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 46.dp, bottom = 16.dp, start = 6.dp, end = 6.dp)
            .background(White100)
    ) {
        BackButton(backgroundColor = Gray90, onClick = {
            viewModel.moveToEnterPhone()
        })
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(id = R.string.country),
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
        )
        CountryList()
    }
}