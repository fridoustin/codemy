package com.sem5.codemy.features.profile.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.sem5.codemy.features.auth.data.User
import kotlinx.coroutines.tasks.await

suspend fun getUserProfile(userId: String): User? {
    val firestore = FirebaseFirestore.getInstance()
    return try {
        val document = firestore.collection("users").document(userId).get().await()
        document.toObject(User::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}