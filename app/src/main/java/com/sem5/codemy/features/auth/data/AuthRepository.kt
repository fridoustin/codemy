package com.sem5.codemy.features.auth.data

import android.service.autofill.UserData
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun getProfile(userData: UserData)
    suspend fun refreshProfile()
    suspend fun signIn(signInData: SignInData)
    suspend fun signUp(signUpData: SignUpData)
    suspend fun resetPassword(resetPasswordData: ResetPasswordData)
}