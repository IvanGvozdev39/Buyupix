package com.ivangvozdev.buyupix.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivangvozdev.buyupix.R
import com.ivangvozdev.buyupix.domain.model.Country
import com.ivangvozdev.buyupix.presentation.viewmodel.LoginViewModel
import com.ivangvozdev.buyupix.ui.theme.Black5
import com.ivangvozdev.buyupix.ui.theme.Blue90
import com.ivangvozdev.buyupix.ui.theme.Gray50
import com.ivangvozdev.buyupix.ui.theme.White100
import com.ivangvozdev.buyupix.ui.theme.robotoFamily

@Composable
fun CountryItem(country: Country, viewModel: LoginViewModel = hiltViewModel()) {

    val selectedCountry by viewModel.selectedCountry.collectAsState()

    Box(modifier = Modifier
        .padding(horizontal = 4.dp, vertical = 4.dp)
        .clip(RoundedCornerShape(8.dp))
        .clickable {
            viewModel.selectCountry(country.code)
            viewModel.moveToEnterPhone()
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White100, shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = country.flagIconRes),
                contentDescription = stringResource(id = R.string.flag),
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = stringResource(id = country.countryNameRes),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Black5,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = country.phoneCode,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Gray50,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (selectedCountry.code == country.code) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = stringResource(id = R.string.check_icon),
                    modifier = Modifier.size(24.dp)
                        .align(Alignment.CenterVertically),
                    tint = Blue90
                )
            }

        }
    }
}