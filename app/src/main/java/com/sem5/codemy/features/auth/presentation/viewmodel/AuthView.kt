package com.sem5.codemy.features.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.sem5.codemy.features.auth.data.SignInData
import com.sem5.codemy.features.auth.data.SignUpData
import com.sem5.codemy.features.auth.domain.SignInResult
import com.sem5.codemy.features.auth.domain.SignInUseCase
import kotlinx.coroutines.launch

class AuthView: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    init {
        checkAuthState()
    }

    fun checkAuthState(){
        val user = auth.currentUser
        if(user == null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
            _userName.value = user.displayName
            _userEmail.value = user.email
        }
    }

    fun login(data: SignInData){

        if(data.email.isEmpty() || data.password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            try{
                auth.signInWithEmailAndPassword(data.email, data.password)
                    .addOnCompleteListener{task->
                        if(task.isSuccessful){
                            val user = auth.currentUser
                            _authState.value = AuthState.Authenticated

                            user?.let{
                                _userName.value = it.displayName
                                _userEmail.value = it.email
                            }
                        }else{
                            _authState.value =
                                AuthState.Error(task.exception?.message ?: "Something went wrong")
                        }
                    }
            } catch (e: Exception){
                _authState.value = AuthState.Error(e.message ?: "Unexpected Error Occurred")
            }
        }
    }

    fun signUp(data: SignUpData){
        if(data.email.isEmpty() || data.password.isEmpty()){
            _authState.value = AuthState.Error("Name, Email or Password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(data.email, data.password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    user?.let{
                        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                            .setDisplayName(data.name)
                            .build()

                        it.updateProfile(profileUpdates)
                            .addOnCompleteListener{ updateTask->
                                if(updateTask.isSuccessful){
                                    _authState.value = AuthState.Authenticated
                                    _userName.value = data.name
                                }else{
                                    _authState.value =
                                        AuthState.Error(updateTask.exception?.message ?: "Something went wrong")
                                }
                            }
                    }
                }
                else{
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signOut(navController: NavController){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}