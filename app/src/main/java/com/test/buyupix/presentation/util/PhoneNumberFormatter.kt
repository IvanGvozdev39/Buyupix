package com.test.buyupix.presentation.util

object PhoneNumberFormatter {
    fun formatBelarusianNumber(digits: String): String {
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

    fun formatRussianNumber(digits: String): String {
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
            else -> "(${digits.substring(0, 3)}) ${digits.substring(3, 6)}-${digits.substring(6, 8)}-${digits.substring(8)}"
        }
    }

    fun formatUSNumber(digits: String): String {
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