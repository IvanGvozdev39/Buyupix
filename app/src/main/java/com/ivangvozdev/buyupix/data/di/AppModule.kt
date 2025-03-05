package com.ivangvozdev.buyupix.data.di

import com.google.firebase.auth.FirebaseAuth
import com.ivangvozdev.buyupix.domain.formatter.PhoneNumberFormatterFactory
import com.ivangvozdev.buyupix.domain.usecase.FormatPhoneNumberUseCase
import com.ivangvozdev.buyupix.domain.usecase.GetCountryByCodeUseCase
import com.ivangvozdev.buyupix.domain.usecase.GetDefaultCountryUseCase
import com.ivangvozdev.buyupix.domain.usecase.VerifyCodeUseCase
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
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

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

    @Provides
    @Singleton
    fun providePhoneNumberFormatterFactory(): PhoneNumberFormatterFactory {
        return PhoneNumberFormatterFactory
    }

    @Provides
    @Singleton
    fun provideVerifyCodeUseCase(auth: FirebaseAuth): VerifyCodeUseCase {
        return VerifyCodeUseCase(auth)
    }

    @Provides
    @Singleton
    fun provideFormatPhoneNumberUseCase(formatterFactory: PhoneNumberFormatterFactory): FormatPhoneNumberUseCase {
        return FormatPhoneNumberUseCase(formatterFactory)
    }
}