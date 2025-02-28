package com.test.buyupix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.buyupix.domain.model.Language
import com.test.buyupix.domain.usecase.GetCurrentLanguageUseCase
import com.test.buyupix.domain.usecase.GetDefaultLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
    getDefaultLanguageUseCase: GetDefaultLanguageUseCase
) : ViewModel() {

    private val _selectedLanguage = MutableStateFlow(getDefaultLanguageUseCase())
    val selectedLanguage: MutableStateFlow<Language?> = _selectedLanguage

    fun selectLanguage(code: String) {
        viewModelScope.launch {
            val language = getCurrentLanguageUseCase(code)
            _selectedLanguage.value = language
        }
    }
}