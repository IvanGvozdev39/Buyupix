package com.test.buyupix.data.repository

import com.test.buyupix.domain.model.Language
import com.test.buyupix.R

object LanguageRepository {
    private val supportedLanguages = listOf(
        Language(code = "BY", flagIconRes = R.drawable.ic_flag_by, phoneCode = "+375"),
        Language(code = "RU", flagIconRes = R.drawable.ic_flag_ru, phoneCode = "+7"),
        Language(code = "US", flagIconRes = R.drawable.ic_flag_us, phoneCode = "+1")
    )

    fun getLanguageByCode(code: String): Language? {
        return supportedLanguages.find { it.code == code }
    }
}