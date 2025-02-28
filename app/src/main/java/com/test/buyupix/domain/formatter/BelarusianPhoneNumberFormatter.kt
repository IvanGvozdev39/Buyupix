package com.test.buyupix.domain.formatter

class BelarusianPhoneNumberFormatter : PhoneNumberFormatter {
    override fun format(digits: String): String {
        return when (digits.length) {
            0 -> ""
            1 -> "($digits"
            2 -> "($digits"
            3 -> "(${digits.substring(0, 2)}) ${digits[2]}"
            4 -> "(${digits.substring(0, 2)}) ${digits.substring(2, 4)}"
            5 -> "(${digits.substring(0, 2)}) ${digits.substring(2, 5)}"
            6 -> "(${digits.substring(0, 2)}) ${digits.substring(2, 5)}-${digits[5]}"
            7 -> "(${digits.substring(0, 2)}) ${digits.substring(2, 5)}-${digits.substring(5, 7)}"
            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 5)}-${digits.substring(5, 7)}-${digits.substring(7)}"
        }
    }
}