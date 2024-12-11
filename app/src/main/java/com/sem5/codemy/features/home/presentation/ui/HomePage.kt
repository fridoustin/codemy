package com.sem5.codemy.features.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.NewsData
import com.sem5.codemy.features.home.presentation.component.Carousel
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.features.home.presentation.component.BigLessonCard
import com.sem5.codemy.features.home.presentation.component.LessonCard
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.components.SearchBar
import com.sem5.codemy.ui.theme.montserratFontFamily

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthView,
){
    val authState = authViewModel.authState.observeAsState()
    val userName = authViewModel.userName.observeAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val newsData = listOf(
        NewsData(R.drawable.news1),
        NewsData(R.drawable.news2),
        NewsData(R.drawable.news3),
    )
    val lessonCategories = listOf(
        LessonCategories(R.drawable.c, R.string.c),
        LessonCategories(R.drawable.java, R.string.java),
        LessonCategories(R.drawable.js, R.string.js),
        LessonCategories(R.drawable.python, R.string.python),
    )

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("signin")
            is AuthState.Authenticated -> {
                authViewModel.loadUserNameFromFirebase()
            }
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Hello 👋 , ${userName.value ?: "User"}",
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 16.dp)
                    ){
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
            BottomBar(
                currentScreen = currentRoute ?: "",
                navController = navController,
                onItemSelected = { selectedScreen ->
                    if (selectedScreen != currentRoute) {
                        navController.navigate(selectedScreen)
                    }
                }
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding),
        ) {

            SearchBar(
                navController
            )


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {

                item {
                    Carousel(newsData = newsData)
                }

                item{
                    Text(
                        modifier = Modifier
                            .padding(start = 26.dp, top = 16.dp),
                        text = "Bahasa Pemrograman",
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow (
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 26.dp)
                    ){
                        items(lessonCategories){ item ->
                            LessonCard(
                                lessonCategories = item,
                                modifier = Modifier,
                                onClick = {}
                            )
                        }
                    }
                }

                item{
                    Text(
                        text = "Topik Khusus",
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 26.dp, top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BigLessonCard(navController)
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}