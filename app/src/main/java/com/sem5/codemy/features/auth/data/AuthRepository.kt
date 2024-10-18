package com.sem5.codemy.features.auth.data

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun signIn(SignInData: SignInData)
    suspend fun signUp(SignUpData: SignUpData)
    suspend fun resetPassword(ResetPasswordData: ResetPasswordData)
}