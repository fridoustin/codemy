//package com.sem5.codemy.features.profile.presentation.components
//
//import android.graphics.Bitmap
//import android.net.Uri
//import androidx.activity.ComponentActivity
//import com.google.firebase.storage.FirebaseStorage
//import kotlinx.coroutines.tasks.await
//
//suspend fun uploadImageToFirebase(
////    bitmap: Bitmap,
////    context: ComponentActivity,
////    callback: (Boolean) -> Unit,
//
//    uri : Uri,
//    onSuccess : (String) -> Unit,
//    onFailure : (Exception) -> Unit
//){
//    val storageRef = FirebaseStorage.getInstance().reference.child("profile_images/${uri.lastPathSegment}")
//
//    try {
//        storageRef.putFile(uri).await()
//        val downloadUrl = storageRef.downloadUrl.await()
//        onSuccess(downloadUrl.toString())
//    } catch (e: Exception){
//        onFailure(e)
//    }
//}