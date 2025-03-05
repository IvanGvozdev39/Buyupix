package com.ivangvozdev.buyupix.domain.formatter

interface PhoneNumberFormatter {
    fun format(digits: String): String
}