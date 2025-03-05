package com.ivangvozdev.buyupix.domain.model

data class Country(
    val code: String,
    val countryNameRes: Int,
    val flagIconRes: Int,
    val phoneCode: String,
    val maxLengthNoCode: Int
)