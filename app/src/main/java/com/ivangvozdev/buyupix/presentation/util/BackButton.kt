package com.ivangvozdev.buyupix.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivangvozdev.buyupix.R

@Composable
fun BackButton(boxSize: Dp = 32.dp, iconSize: Dp = 16.dp, onClick: () -> Unit, backgroundColor: Color) {
    Box(modifier = Modifier
        .size(boxSize)
        .background(backgroundColor, shape = CircleShape)
        .clickable { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = stringResource(id = R.string.arrow_left),
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
                .padding(end = 4.dp)
        )
    }
}