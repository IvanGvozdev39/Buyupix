package com.test.buyupix.domain.usecase

import com.test.buyupix.domain.model.Language
import com.test.buyupix.data.repository.LanguageRepository

class GetDefaultLanguageUseCase(private val languageRepository: LanguageRepository) {
    operator fun invoke(): Language? {
        return languageRepository.getLanguageByCode("BY")
    }
}