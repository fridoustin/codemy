package com.sem5.codemy.features.auth.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

data class SignInData(
    val email: String,
    val password: String
)

data class SignUpData(
    val name: String,
    val email: String,
    val password: String
)

data class ResetPasswordData(
    val email: String
)

data class User(
    val id: String? = null,
    val name: String? = null,
    val email: String,
    val password: String,
    val photo: String? = null
)

fun storeUserInFirestore(userData: User) {
    val firestore = FirebaseFirestore.getInstance()
    userData.id?.let {
        firestore.collection("users").document(it)
        .set(userData)  // Ensure userData is a User object or Map<String, Object>
        .addOnSuccessListener {
            Log.d("Firestore", "User added successfully")
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error adding user", e)
        }
    }
}
