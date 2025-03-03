package com.test.buyupix.domain.usecase

import com.test.buyupix.data.repository.CountryEnum
import com.test.buyupix.domain.model.Country

class GetCountryByCodeUseCase {
    operator fun invoke(code: String): Country? {
        return CountryEnum.getCountryByCode(code)?.toDomainModel()
    }
}