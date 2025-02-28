package com.test.buyupix.domain.usecase

import LanguageRepository
import com.test.buyupix.domain.model.Language

class GetDefaultLanguageUseCase {
    operator fun invoke(): Language? {
        return LanguageRepository.BY.toDomainModel()
    }
}