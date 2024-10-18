package com.sem5.codemy.features.auth.domain

import com.sem5.codemy.features.auth.data.AuthRepository
import com.sem5.codemy.features.auth.data.SignInData
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(signInData: SignInData) {
        authRepository.signIn(signInData)
    }
}