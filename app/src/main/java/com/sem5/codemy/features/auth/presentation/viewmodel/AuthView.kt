package com.sem5.codemy.features.auth.presentation.viewmodel

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.UserData
import com.google.firebase.firestore.model.mutation.MutationBatch
import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.features.auth.data.storeUserInFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

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

//    private fun storeUserInFirestore(userData: User){
//        userData.id?.let {
//            firestore.collection("user")
//                .document(it)
//                .set(it)
//                .addOnSuccessListener{
//                    _authState.value = AuthState.Authenticated
//                }
//                .addOnFailureListener{ exception ->
//                    _authState.value = AuthState.Error(exception.message ?: "Failed to Store Data")
//                }
//        }
//
//    }

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
//                            val firebaseUser = auth.currentUser
//                            firebaseUser?.let{
//                                _authState.value = AuthState.Authenticated
//                                _userName.value = it.displayName
//                                _userEmail.value = it.email
//                                _userPhoto.value = it.photoUrl?.toString()
//                            }
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

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        _userName.value = null
        _userEmail.value = ""
        _userPhoto.value = null
    }


//    fun handleGoogleSignIn(context: Context, navController: NavController){
//        viewModelScope.launch {
//            googleSignIn(context).collect{ result ->
//                result.fold(
//                    onSuccess = { authResult ->
//                        val currentUser = authResult.user
//                        if (currentUser != null){
//                            user.value = User(currentUser.uid, currentUser)
//                        }
//
//                    } onFailure
//                )
//            }
//        }
//    }

//    private suspend fun googleSignIn(context: Context): Flow<Result<AuthResult>> {
//        return callbackFlow {
//            try {
//                val credentialManager: CredentialManager = CredentialManager.create(context)
//
//                val ranNonce: String = UUID.randomUUID().toString()
//                val bytes: ByteArray = ranNonce.toByteArray()
//                val md: MessageDigest = MessageDigest.getInstance("SHA-256")
//                val digest: ByteArray = md.digest(bytes)
//                val hashedNonce: String = digest.fold("") {str, it -> str + "%02x".format(it)}
//
//                val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
//                    .setFilterByAuthorizedAccounts(false)
//                    .setServerClientId(context.getString(R.string.web_client_id))
//                    .setNonce(hashedNonce)
//                    .setAutoSelectEnabled(true)
//                    .build()
//
//                val request: GetCredentialRequest = GetCredentialRequest.Builder()
//                    .addCredentialOption(googleIdOption)
//                    .build()
//
//                val result = credentialManager.getCredential(context, request)
//                val credential = result.credential
//
//                if(credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
//                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
//                    val authResult = auth.signInWithCredential(authCredential).await()
//                    trySend(Result.success(authResult))
//                } else {
//                    throw RuntimeException("invalid credential")
//                }
//            } catch (e: GetCredentialCancellationException){
//                trySend(Result.failure(Exception("Sign In Error")))
//            } catch (e: Exception){
//                trySend(Result.failure(e))
//            }
//
//            awaitClose {  }
//        }
//    }

}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}