package com.ivangvozdev.buyupix.presentation.util

import com.ivangvozdev.buyupix.data.enumeration.CountryEnum
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ivangvozdev.buyupix.presentation.util.animaton.useSlideInAnimation
import com.ivangvozdev.buyupix.ui.theme.Gray90
import kotlin.math.roundToInt

@Composable
fun CountryList() {
    val countries = remember { CountryEnum.provideEntries() }

    val offsetY = useSlideInAnimation(initialOffset = -200f)

    LazyColumn(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 20.dp)
            .fillMaxWidth()
            .offset { IntOffset(0, offsetY.value.roundToInt()) }
            .border(1.dp, Gray90, shape = RoundedCornerShape(10.dp))

    ) {
        items(countries,
            key = { country -> country.code }) { country ->
            CountryItem(country = country)
        }
    }
}