package com.ivangvozdev.buyupix.data.enumeration

import com.ivangvozdev.buyupix.domain.model.Country
import com.ivangvozdev.buyupix.R

enum class CountryEnum(private val code: String, private val countryNameRes: Int, private val flagIconRes: Int, private val phoneCode: String, private val maxLengthNoCode: Int) {
    BY("BY", R.string.belarus, R.drawable.ic_flag_by, "+375", 14),
    RU("RU", R.string.russia, R.drawable.ic_flag_ru, "+7", 15),
    US("US", R.string.usa, R.drawable.ic_flag_us, "+1", 14);

    companion object {
        fun getCountryByCode(code: String): Country {
            return entries.find { it.code == code }?.toDomainModel() ?: BY.toDomainModel()
        }

        fun getDefaultCountry(): Country {
            return BY.toDomainModel()
        }

        fun provideEntries(): List<Country> {
            val countries = mutableListOf<Country>()
            entries.forEach { entry ->
                countries.add(entry.toDomainModel())
            }
            return countries
        }
    }

    fun toDomainModel(): Country {
        return Country(code, countryNameRes, flagIconRes, phoneCode, maxLengthNoCode)
    }
}