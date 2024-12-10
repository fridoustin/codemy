package com.sem5.codemy.features.profile.data.remote

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.sem5.codemy.features.auth.data.User
import kotlinx.coroutines.tasks.await

suspend fun getUserDocument(userId: String): DocumentSnapshot? {
    return try {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId).get().await()
    } catch (e: Exception) {
        Log.e("FirestoreError", "Error fetching user document: ${e.message}")
        null
    }
}