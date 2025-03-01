package com.test.buyupix.domain.usecase

import com.test.buyupix.data.repository.CountryRepository
import com.test.buyupix.domain.model.Country

class GetCountryByCodeUseCase {
    operator fun invoke(code: String): Country? {
        return CountryRepository.getCountryByCode(code)?.toDomainModel()
    }
}