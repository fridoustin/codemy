package com.sem5.codemy.features.article.data.remote

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getArticles(): List<Map<String, Any>> {
    return try {
        val db = FirebaseFirestore.getInstance()
        val snapshot = db.collection("artikel").get().await()
        snapshot.documents.map { document ->
            val data = document.data
            if (data != null) {
                mapOf(
                    "id" to document.id,
                    "title" to (data["title"] as? String ?: "Untitled"),
                    "description" to (data["description"] as? String ?: "No description available."),
                    "category" to (data["category"] as? String ?: "Uncategorized")
                )
            } else {
                Log.e("FirestoreError", "Invalid data format for document: ${document.id}")
                emptyMap<String, Any>()
            }
        }
    } catch (e: Exception) {
        Log.e("FirestoreError", "Error fetching articles: ${e.message}")
        emptyList()
    }
}
