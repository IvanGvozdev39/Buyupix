package com.ivangvozdev.buyupix.domain.usecase

import com.ivangvozdev.buyupix.data.enumeration.CountryEnum
import com.ivangvozdev.buyupix.domain.model.Country

class GetCountryByCodeUseCase {
    operator fun invoke(code: String): Country? {
        return CountryEnum.getCountryByCode(code)?.toDomainModel()
    }
}