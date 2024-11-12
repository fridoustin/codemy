package com.sem5.codemy.features.profile.domain

import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.features.profile.data.repository.UserRepository

class GetUserProfileUseCase (private val userRepository: UserRepository){
    suspend operator fun invoke(userId: String): User? {
        return userRepository.getUserProfile(userId)
    }
}