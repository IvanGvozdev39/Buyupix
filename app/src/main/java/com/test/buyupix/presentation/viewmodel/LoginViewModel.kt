package com.test.buyupix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.buyupix.domain.LoginStage
import com.test.buyupix.domain.model.Country
import com.test.buyupix.domain.usecase.GetCountryByCodeUseCase
import com.test.buyupix.domain.usecase.GetDefaultCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getCountryByCodeUseCase: GetCountryByCodeUseCase,
    getDefaultCountryUseCase: GetDefaultCountryUseCase
) : ViewModel() {

    private val _currentStep = MutableStateFlow(LoginStage.ENTER_PHONE)
    val currentStep: StateFlow<LoginStage> get() = _currentStep

    private val _selectedCountry = MutableStateFlow(getDefaultCountryUseCase())
    val selectedCountry: MutableStateFlow<Country> = _selectedCountry

    fun moveToSelectCountry() {
        _currentStep.value = LoginStage.SELECT_COUNTRY
    }

    fun moveToEnterPhone() {
        _currentStep.value = LoginStage.ENTER_PHONE
    }

    fun moveToConfirmCode() {
        _currentStep.value = LoginStage.CONFIRM_CODE
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            val country = getCountryByCodeUseCase(code)
            if (country != null) {
                _selectedCountry.value = country
            }
        }
    }
}