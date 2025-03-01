package com.test.buyupix.domain.usecase

import com.test.buyupix.data.repository.CountryRepository
import com.test.buyupix.domain.model.Country

class GetDefaultCountryUseCase {
    operator fun invoke(): Country {
        return CountryRepository.BY.toDomainModel()
    }
}