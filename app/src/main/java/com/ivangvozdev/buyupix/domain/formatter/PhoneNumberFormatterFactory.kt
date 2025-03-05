package com.ivangvozdev.buyupix.domain.formatter

object PhoneNumberFormatterFactory {
    fun createFormatter(countryCode: String): PhoneNumberFormatter {
        return when (countryCode) {
            "BY" -> BelarusianPhoneNumberFormatter()
            "RU" -> RussianPhoneNumberFormatter()
            "US" -> USPhoneNumberFormatter()
            else -> BelarusianPhoneNumberFormatter()
        }
    }
}