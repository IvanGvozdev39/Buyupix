package com.ivangvozdev.buyupix.presentation.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ivangvozdev.buyupix.R
import com.ivangvozdev.buyupix.ui.theme.Black5
import com.ivangvozdev.buyupix.ui.theme.Gray80
import kotlinx.coroutines.delay

@Composable
fun CustomToast(message: String, duration: Long = 6000L) {
    var isVisible by remember { mutableStateOf(true) }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500), label = stringResource(id = R.string.toast_alpha_anim)
    )

    LaunchedEffect(isVisible) {
        delay(duration)
        isVisible = false
    }

    if (alpha > 0f) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 32.dp, start = 16.dp, end = 16.dp)) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(
                        color = Gray80.copy(alpha = alpha),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(12.dp)
                    .graphicsLayer(alpha = alpha)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = message,
                    color = Black5.copy(alpha = alpha)
                )
            }
        }
    }
}