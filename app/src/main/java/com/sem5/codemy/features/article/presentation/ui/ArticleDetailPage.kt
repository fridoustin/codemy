package com.sem5.codemy.features.article.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.components.TopBar
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ArticleDetailPage(articleId: String, navController: NavController) {
    // State untuk menyimpan detail artikel
    var articleTitle by remember { mutableStateOf("") }
    var articleDescription by remember { mutableStateOf("") }
    var articleContent by remember { mutableStateOf("") }

    // Ambil data dari Firestore berdasarkan articleId
    LaunchedEffect(articleId) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("artikel").document(articleId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    articleTitle = document.getString("title") ?: ""
                    articleDescription = document.getString("description") ?: ""
                    articleContent = document.getString("content") ?: ""
                }
            }
            .addOnFailureListener { exception ->
                // Tangani error jika diperlukan
            }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Yuk, Literasi !",
                actions = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Card untuk Judul dan Deskripsi
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = articleTitle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = articleDescription,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Card untuk Konten Lengkap
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = articleContent,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
