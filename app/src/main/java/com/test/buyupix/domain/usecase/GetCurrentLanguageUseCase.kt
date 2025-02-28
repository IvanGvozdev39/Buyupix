package com.test.buyupix.domain.usecase

import LanguageRepository
import com.test.buyupix.domain.model.Language

class GetCurrentLanguageUseCase {
    operator fun invoke(code: String): Language? {
        return LanguageRepository.getLanguageByCode(code)?.toDomainModel()
    }
}