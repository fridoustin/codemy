package com.sem5.codemy.features.auth.domain

import com.sem5.codemy.features.auth.data.AuthRepository
import com.sem5.codemy.features.auth.data.SignInData
import javax.inject.Inject

sealed class SignInResult {
    object Success : SignInResult()
    data class Error(val message: String) : SignInResult()
}

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(signInData: SignInData): SignInResult {
        if(signInData.email.isEmpty() || signInData.password.isEmpty()){
            return SignInResult.Error("Email or Password can't be empty")
        }
        return try {
            authRepository.signIn(signInData)
            SignInResult.Success
        } catch (e: Exception) {
            SignInResult.Error(e.message ?: "Unexpected Error Occurred")
        }
    }
}