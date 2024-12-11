package com.sem5.codemy.features.article.presentation.ui

import android.util.Log
import com.sem5.codemy.ui.theme.components.TopBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.LightBlue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.components.BottomBar
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.*
import com.sem5.codemy.features.article.data.remote.getArticles
import com.sem5.codemy.features.article.presentation.ui.components.ArticleRow


data class Article(
    val articleTitle: String, 
    val articleDescription: String,
    val category: String, 
    val route: String? = null 
)

@Composable
fun ArticleHomePage(modifier: Modifier = Modifier, navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isLoading = remember { mutableStateOf(true) }

    // State untuk menyimpan artikel
    var articleList by remember { mutableStateOf<List<Article>>(emptyList()) }

    // Mengambil data dari Firestore
    LaunchedEffect(Unit) {
        try {
            isLoading.value = true
            val articles = getArticles()
            articleList = articles.map { data ->
                Article(
                    articleTitle = data["title"] as? String ?: "",
                    articleDescription = data["description"] as? String ?: "",
                    route = data["id"] as? String,
                    category = data["category"] as? String ?: "Umum"
                )
            }
        } catch (e: Exception) {
            Log.e("ArticleHomePage", "Error fetching articles: ", e)
        } finally {
            isLoading.value = false
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Artikel, Yuk Sinau !",
                actions = {
                    IconButton(
                        onClick = {  },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = DarkBlue
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar (
                currentScreen = currentRoute ?: "",
                navController = navController,
                onItemSelected = { selectedScreen ->
                    if (selectedScreen != currentRoute) {
                        navController.navigate(selectedScreen)
                    }
                }
            )
        }
    ) { innerPadding ->
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEFF4FA)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEFF4FA))
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(articleList) { article ->
                        ArticleRow(
                            article = article,
                            onReadMoreClick = {
                                // Asumsikan Anda menyimpan ID dokumen article di dalam Article.route atau membuat field baru
                                val articleId = article.route ?: ""
                                navController.navigate("articledetail/$articleId")
                            }
                        )
                    }
                }
            }
        }
    }
}