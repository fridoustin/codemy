package com.sem5.codemy.features.article.presentation.ui

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
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.R
import com.sem5.codemy.ui.theme.LightBlue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.components.BottomBar

data class Article(
    val articleTitle: Int, // Resource ID for the title
    val articleDescription: String, // Description of the article
    val route: String? = null // Navigation route
)

@Composable
fun ArticleHomePage(modifier: Modifier = Modifier, navController: NavController) {
    // Sample list of articles
    val articleList = listOf(
        Article(R.string.crashhtml, "Belajar HTML hanya dalam waktu 5 menit!"),
        Article(R.string.api, "Mengenal apa itu API dalam dunia pemrograman"),
        Article(R.string.mitm, "Apa itu MITM? Bagaimana cara menghindarinya?"),
        Article(R.string.gausahcss, "Kenapa sekarang banyak developer tidak belajar CSS?"),
        Article(R.string.web3wow, "Kenapa Web3 disebut sebagai masa depan internet?")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopBar(
                title = "Artikel, Yuk Sinau !",
                actions = {
                    IconButton(
                        onClick = { navController.navigate("notification") },
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
                    ArticleRow (
                        article = article,
                        onClick = {
                            article.route?.let { navController.navigate(it) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleRow(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Atur warna latar belakang
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = article.articleTitle), // Ambil title dari strings.xml
                maxLines = 1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Tambahkan Divider untuk memisahkan judul dan deskripsi
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = article.articleDescription,
                fontSize = 14.sp,
                maxLines = 3,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}