package com.test.buyupix.presentation.util.animaton

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun useSlideInAnimation(
    initialOffset: Float,
    targetOffset: Float = 0f,
    durationMillis: Int = 40,
    easing: Easing = LinearEasing
): State<Float> {
    val isAnimated = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isAnimated.value = true
    }

    return animateFloatAsState(
        targetValue = if (isAnimated.value) targetOffset else initialOffset,
        animationSpec = tween(durationMillis, easing = easing),
        label = "slideInAnimation"
    )
}