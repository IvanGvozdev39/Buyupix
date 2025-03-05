package com.ivangvozdev.buyupix.domain.usecase

import com.ivangvozdev.buyupix.data.enumeration.CountryEnum
import com.ivangvozdev.buyupix.domain.model.Country

class GetDefaultCountryUseCase {
    operator fun invoke(): Country {
        return CountryEnum.BY.toDomainModel()
    }
}