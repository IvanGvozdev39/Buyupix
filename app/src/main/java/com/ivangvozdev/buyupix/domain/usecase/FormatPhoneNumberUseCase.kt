package com.ivangvozdev.buyupix.domain.usecase

import com.ivangvozdev.buyupix.domain.formatter.PhoneNumberFormatterFactory
import javax.inject.Inject

class FormatPhoneNumberUseCase @Inject constructor(
    private val phoneNumberFormatterFactory: PhoneNumberFormatterFactory
) {
    operator fun invoke(countryCode: String, rawDigits: String): String {
        val formatter = phoneNumberFormatterFactory.createFormatter(countryCode)
        return formatter.format(rawDigits)
    }
}