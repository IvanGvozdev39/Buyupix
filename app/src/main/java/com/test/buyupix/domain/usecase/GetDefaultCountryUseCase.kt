package com.test.buyupix.domain.usecase

import com.test.buyupix.data.repository.CountryEnum
import com.test.buyupix.domain.model.Country

class GetDefaultCountryUseCase {
    operator fun invoke(): Country {
        return CountryEnum.BY.toDomainModel()
    }
}