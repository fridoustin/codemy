package com.sem5.codemy.features.profile.data.repository

import android.net.Uri
import com.sem5.codemy.features.auth.data.User

interface UserRepository {
    suspend fun getUserProfile(userId: String): User?
    suspend fun updateProfilePicture(userId: String, imageUri: Uri): String
}