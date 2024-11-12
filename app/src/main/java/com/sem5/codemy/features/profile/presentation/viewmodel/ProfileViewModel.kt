package com.sem5.codemy.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.features.profile.domain.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase
): ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun loadUserProfile(userId: String){
        viewModelScope.launch {
            _user.value = getUserProfileUseCase(userId)
        }
    }
}