package com.sem5.codemy.features.article.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.components.TopBar
import com.google.firebase.firestore.FirebaseFirestore
import com.sem5.codemy.R // Pastikan import R

@Composable
fun ArticleDetailPage(articleId: String, navController: NavController) {
    var articleTitle by remember { mutableStateOf("") }
    var articleDescription by remember { mutableStateOf("") }
    var articleContent by remember { mutableStateOf("") }
    var articleCategory by remember { mutableStateOf("") }

    LaunchedEffect(articleId) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("artikel").document(articleId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    articleTitle = document.getString("title") ?: ""
                    articleDescription = document.getString("description") ?: ""
                    articleContent = document.getString("content") ?: ""
                    articleCategory = document.getString("category") ?: "Umum"
                }
            }
            .addOnFailureListener { exception ->
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
            // Tampilkan Gambar sesuai Kategori
            val categoryImage = getCategoryImagePainter(articleCategory)
            categoryImage?.let {
                Image(
                    painter = it,
                    contentDescription = articleCategory,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                )
            }

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

// Fungsi untuk memetakan kategori ke gambar
@Composable
fun getCategoryImagePainter(category: String): Painter? {
    return when(category.lowercase()) {
        "programming" -> painterResource(R.drawable.c)
        "webdev" -> painterResource(R.drawable.web) 
        "security" -> painterResource(R.drawable.cyberimg) 
        "game" -> painterResource(R.drawable.gameimg) 
        else -> painterResource(R.drawable.studyicon) 
    }
}
