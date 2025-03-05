package com.ivangvozdev.buyupix.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class VerifyCodeUseCase @Inject constructor(
    private val auth: FirebaseAuth
) {
    operator fun invoke(
        verificationId: String?,
        code: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val credential = verificationId?.let {
            PhoneAuthProvider.getCredential(it, code)
        } ?: run {
            onError("Invalid verification ID")
            return
        }

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Verification failed")
                }
            }
    }
}