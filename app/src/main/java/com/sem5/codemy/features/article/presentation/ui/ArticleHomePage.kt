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
import com.sem5.codemy.features.article.presentation.ui.components.ArticleRow


// Mendefinisikan data class Article yang benar
data class Article(
    val articleTitle: String, // Title yang diambil sebagai string
    val articleDescription: String, // Deskripsi artikel
    val route: String? = null // Optional: Route untuk navigasi
)

@Composable
fun ArticleHomePage(modifier: Modifier = Modifier, navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val db = FirebaseFirestore.getInstance()

    // State untuk menyimpan artikel
    var articleList by remember { mutableStateOf<List<Article>>(emptyList()) }

    // Mengambil data dari Firestore
    LaunchedEffect(Unit) {
        db.collection("artikel")
            .get()
            .addOnSuccessListener { result ->
                articleList = result.map { document ->
                    val title = document.getString("title") ?: "" // Ambil title sebagai string
                    val description = document.getString("description") ?: ""
                    val route = document.getString("route")
                    // Membuat objek Article dengan title sebagai string
                    Article(articleTitle = title, articleDescription = description, route = route)
                }
            }
            .addOnFailureListener { exception ->
                // Tangani error
            }
    }

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
                    ArticleRow(
                        article = article,
                        onReadMoreClick = {
                            article.route?.let { navController.navigate(it) }
                        }
                    )
                }
            }
        }
    }
}

// fun ArticleRow(article: Article, onClick: () -> Unit) {
//     Card(
//         modifier = Modifier
//             .fillMaxWidth()
//             .height(180.dp)
//             .padding(8.dp),
//         colors = CardDefaults.cardColors(containerColor = Color.White),
//         onClick = onClick
//     ) {
//         Column(
//             modifier = Modifier
//                 .fillMaxSize()
//                 .padding(16.dp)
//         ) {
//             Text(
//                 text = article.articleTitle, // Judul artikel
//                 maxLines = 1,
//                 fontSize = 18.sp,
//                 fontWeight = FontWeight.Bold,
//                 modifier = Modifier.padding(bottom = 8.dp)
//             )

//             // Divider untuk memisahkan judul dan deskripsi
//             HorizontalDivider(
//                 color = Color.LightGray,
//                 thickness = 1.dp,
//                 modifier = Modifier.padding(vertical = 8.dp)
//             )

//             Text(
//                 text = article.articleDescription, // Deskripsi singkat artikel
//                 fontSize = 14.sp,
//                 maxLines = 3,
//                 modifier = Modifier.padding(top = 8.dp)
//             )

//             Spacer(modifier = Modifier.height(8.dp))

//             // Row untuk meletakkan tombol di sebelah kanan
//             Row(
//                 modifier = Modifier.fillMaxWidth(),
//                 horizontalArrangement = Arrangement.End
//             ) {
//                 // Tombol "Read More"
//                 TextButton(
//                     onClick = onClick,
//                     modifier = Modifier.padding(4.dp) // Menambah padding untuk memberikan ruang
//                 ) {
//                     Text(
//                         text = "Read More",
//                         fontSize = 10.sp,
//                         color = Color.Blue // Pastikan warna tombol jelas
//                     )
//                 }
//             }
//         }
//     }
// }