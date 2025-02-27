package com.test.buyupix.domain.usecase

import com.test.buyupix.domain.model.Language
import com.test.buyupix.data.repository.LanguageRepository

class GetCurrentLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    operator fun invoke(code: String): Language? {
        return languageRepository.getLanguageByCode(code)
    }
}