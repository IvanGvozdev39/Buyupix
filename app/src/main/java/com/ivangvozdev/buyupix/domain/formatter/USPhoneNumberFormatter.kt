package com.ivangvozdev.buyupix.domain.formatter

class USPhoneNumberFormatter : PhoneNumberFormatter {
    override fun format(digits: String): String {
        return when (digits.length) {
            0 -> ""
            1 -> "($digits"
            2 -> "($digits"
            3 -> "($digits"
            4 -> "(${digits.substring(0, 3)}) ${digits[3]}"
            5 -> "(${digits.substring(0, 3)}) ${digits.substring(3, 5)}"
            6 -> "(${digits.substring(0, 3)}) ${digits.substring(3, 6)}"
            7 -> "(${digits.substring(0, 3)}) ${digits.substring(3, 6)}-${digits[6]}"
            8 -> "(${digits.substring(0, 3)}) ${digits.substring(3, 6)}-${digits.substring(6, 8)}"
            else -> "(${digits.substring(0, 3)}) ${digits.substring(3, 6)}-${digits.substring(6)}"
        }
    }
}