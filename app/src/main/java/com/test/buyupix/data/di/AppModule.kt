package com.test.buyupix.data.di

import com.test.buyupix.domain.usecase.GetCountryByCodeUseCase
import com.test.buyupix.domain.usecase.GetDefaultCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetCurrentLanguageUseCase(): GetCountryByCodeUseCase {
        return GetCountryByCodeUseCase()
    }

    @Provides
    @Singleton
    fun provideGetDefaultLanguageUseCase(): GetDefaultCountryUseCase {
        return GetDefaultCountryUseCase()
    }

}