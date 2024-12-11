package com.sem5.codemy.features.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.features.auth.data.storeUserInFirestore
import kotlinx.coroutines.launch

class AuthView: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    private val _userPhoto = MutableLiveData<String?>()
    val userPhoto: LiveData<String?> = _userPhoto

    init {
        checkAuthState()
    }

    //mengecek status autentikasi
    fun checkAuthState(){
        val firebaseUser = auth.currentUser
        if(firebaseUser == null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
            _userName.value = firebaseUser.displayName
            _userEmail.value = firebaseUser.email
            _userPhoto.value = firebaseUser.photoUrl?.toString()
        }
    }

    //fungsi untuk login menggunakan firebase
    fun login(data: User){
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
                            checkAuthState()
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

    //fungsi register menggunakan firebase
    fun signUp(data: User){
        if(data.email.isEmpty() || data.password.isEmpty()){
            _authState.value = AuthState.Error("Name, Email or Password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(data.email, data.password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let{
                        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                            .setDisplayName(data.name)
                            .build()

                        it.updateProfile(profileUpdates)
                            .addOnCompleteListener{ updateTask->
                                if(updateTask.isSuccessful){
                                    _authState.value = AuthState.Authenticated
                                    _userName.value = it.displayName
                                    _userEmail.value = it.email
                                    _userPhoto.value = it.photoUrl?.toString()

                                    val userData = User(
                                        id = it.uid,
                                        name = it.displayName ?: "",
                                        email = it.email ?: "",
                                        password = "",
                                        photo = it.photoUrl?.toString()
                                    )
                                    storeUserInFirestore(userData)
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

    //fungsi untuk login menggunakan google
    fun signinWithGoogle(account: GoogleSignInAccount){
        _authState.value = AuthState.Loading
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let {
                        _authState.value = AuthState.Authenticated
                        _userName.value = it.displayName
                        _userEmail.value = it.email
                        _userPhoto.value = it.photoUrl?.toString()

                        val userData = User(
                            id = it.uid,
                            name = it.displayName ?: "",
                            email = it.email ?: "",
                            password = "",
                            photo = it.photoUrl?.toString()
                        )

                        storeUserInFirestore(userData)
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something Went Wrong")
                }
            }
    }

    //fungsi untuk logout
    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        _userName.value = null
        _userEmail.value = ""
        _userPhoto.value = null
    }

    //fungsi untuk mengupdate nama di firestore
    fun updateNameInFirestore(newName: String) {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val userId = firebaseUser.uid
            val userRef = firestore.collection("users").document(userId)
    
            // Update field "name" di Firestore
            userRef.update("name", newName)
                .addOnSuccessListener {
                    _authState.value = AuthState.Authenticated
                    _userName.value = newName // Update state lokal
                }
                .addOnFailureListener { exception ->
                    _authState.value = AuthState.Error(exception.message ?: "Failed to update name")
                }
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    //fungsi untuk mengupdate foto profile di firestore
    fun updatePhotoInFirestore(newName: String) {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val userId = firebaseUser.uid
            val userRef = firestore.collection("users").document(userId)
    
            // Update field "name" di Firestore
            userRef.update("photo", newName)
                .addOnSuccessListener {
                    _authState.value = AuthState.Authenticated
                    _userName.value = newName // Update state lokal
                }
                .addOnFailureListener { exception ->
                    _authState.value = AuthState.Error(exception.message ?: "Failed to update name")
                }
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    //fungsi untuk mendapatkan username dari firestore
    fun loadUserNameFromFirebase() {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val userId = firebaseUser.uid
            firestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val name = document.getString("name")
                        _userName.value = name
                    } else {
                        _userName.value = "User"
                    }
                }
                .addOnFailureListener {
                    _userName.value = "User"
                }
        } else {
            _userName.value = "User"
        }
    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}