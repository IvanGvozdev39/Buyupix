package com.ivangvozdev.buyupix.presentation.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.ivangvozdev.buyupix.domain.enumeration.LoginStage
import com.ivangvozdev.buyupix.domain.model.Country
import com.ivangvozdev.buyupix.domain.usecase.FormatPhoneNumberUseCase
import com.ivangvozdev.buyupix.domain.usecase.GetCountryByCodeUseCase
import com.ivangvozdev.buyupix.domain.usecase.GetDefaultCountryUseCase
import com.ivangvozdev.buyupix.domain.usecase.VerifyCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getCountryByCodeUseCase: GetCountryByCodeUseCase,
    getDefaultCountryUseCase: GetDefaultCountryUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase,
    private val formatPhoneNumberUseCase: FormatPhoneNumberUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _currentStep = MutableStateFlow(LoginStage.ENTER_PHONE)
    val currentStep: StateFlow<LoginStage> get() = _currentStep

    private val _selectedCountry = MutableStateFlow(getDefaultCountryUseCase())
    val selectedCountry: StateFlow<Country> get() = _selectedCountry

    private var verificationId: String? = null

    private val _inputTextString = MutableStateFlow("")
    val inputTextString: StateFlow<String> get() = _inputTextString

    private val _inputNumber = MutableStateFlow("")
    val inputNumber: StateFlow<String> get() = _inputNumber

    private val _continueButtonEnabled = MutableStateFlow(false)
    val continueButtonEnabled: StateFlow<Boolean> get() = _continueButtonEnabled

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> get() = _toastMessage


    //ConfirmCodeSection
    private val _remainingTimeSecs = MutableStateFlow(30)
    val remainingTimeSecs: StateFlow<Int> get() = _remainingTimeSecs

    private val _timerActive = MutableStateFlow(false)
    val timerActive: StateFlow<Boolean> get() = _timerActive

    private fun startCodeResendTimer() {
        viewModelScope.launch {
            while (_remainingTimeSecs.value > 0 && _timerActive.value) {
                delay(1000L)
                _remainingTimeSecs.value--
            }
            _timerActive.value = false
        }
    }

    fun restartCodeResendTimer() {
        _remainingTimeSecs.value = 30
        _timerActive.value = true
        startCodeResendTimer()
    }


    private fun clearInput() {
        _inputNumber.value = ""
        _inputTextString.value = ""
    }

    private fun triggerFailedValidationToast(message: String) {
        _toastMessage.value = message
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }

    fun moveToSelectCountry() {
        _currentStep.value = LoginStage.SELECT_COUNTRY
    }

    fun moveToEnterPhone() {
        _currentStep.value = LoginStage.ENTER_PHONE
    }

    private fun moveToConfirmCode() {
        _currentStep.value = LoginStage.CONFIRM_CODE
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            if (code != selectedCountry.value.code) {
                clearInput()
                _continueButtonEnabled.value = false
            }

            val country = getCountryByCodeUseCase(code)
            _selectedCountry.value = country
        }
    }

    fun sendVerificationCode(phoneNumber: String, activity: ComponentActivity) {
        moveToConfirmCode()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(120L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    onVerificationCompleted(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    moveToEnterPhone()
                    triggerFailedValidationToast(e.message ?: "Verification failed")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    this@LoginViewModel.verificationId = verificationId
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(code: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        verifyCodeUseCase(
            verificationId = verificationId,
            code = code,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    private fun formatPhoneNumber(rawDigits: String): String {
        return formatPhoneNumberUseCase(selectedCountry.value.code, rawDigits)
    }

    fun onPhoneInputChanged(newTextString: String): String {
        val maxLength = selectedCountry.value.maxLengthNoCode
        if (newTextString.length <= maxLength) {
            _continueButtonEnabled.value = newTextString.length == maxLength

            val inputNumberTemp = newTextString.filter { it.isDigit() }
            _inputNumber.value = inputNumberTemp

            val formattedInputText = formatPhoneNumber(inputNumberTemp)
            _inputTextString.value = formattedInputText
            return formattedInputText
        }
        return inputTextString.value
    }
}