package com.sem5.codemy.features.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.sem5.codemy.features.auth.data.SignInData
import kotlinx.coroutines.launch

class AuthView: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthState()
    }

    fun checkAuthState(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
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
                            _authState.value = AuthState.Authenticated
                        }else{
                            _authState.value =
                                AuthState.Error(task.exception?.message ?: "Something went wrong")
                        }
                    }
            } catch (e: Exception){
                _authState.value = AuthState.Error(e.message ?: "Unexpected Error Ocured")
            }
        }
    }

    fun signUp(email : String, password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
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