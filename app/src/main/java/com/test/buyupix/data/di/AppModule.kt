package com.test.buyupix.data.di

import com.test.buyupix.domain.usecase.GetCurrentLanguageUseCase
import com.test.buyupix.domain.usecase.GetDefaultLanguageUseCase
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
    fun provideGetCurrentLanguageUseCase(): GetCurrentLanguageUseCase {
        return GetCurrentLanguageUseCase()
    }

    @Provides
    @Singleton
    fun provideGetDefaultLanguageUseCase(): GetDefaultLanguageUseCase {
        return GetDefaultLanguageUseCase()
    }
}