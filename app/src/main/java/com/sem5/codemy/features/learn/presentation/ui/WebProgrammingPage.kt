package com.sem5.codemy.features.learn.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.features.learn.presentation.components.LessonUi
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.SearchBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.montserratFontFamily

@Composable
fun WebProgrammingPage(modifier: Modifier = Modifier, navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val lessonList = listOf(
        LessonCategories(R.drawable.html, R.string.html1),
        LessonCategories(R.drawable.css, R.string.css1),
        LessonCategories(R.drawable.css, R.string.css2),
        LessonCategories(R.drawable.js, R.string.js1),
        LessonCategories(R.drawable.js, R.string.js2),
        LessonCategories(R.drawable.sqlimg, R.string.sqli),
        LessonCategories(R.drawable.web, R.string.web1)
    )

    Scaffold(
        containerColor = LightBlue,
        topBar = {
            TopBar(
                title = "Siap Menjadi Web Developer",
                actions = {
                    IconButton(
                        onClick = {navController.navigate("notification")},
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding),
        ){
            SearchBar(
                navController
            )

            LazyColumn(){
                item{
                    Text(
                        text = "Web Programming",
                        fontFamily = montserratFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 26.dp, top = 16.dp , bottom = 16.dp)
                    )
                }

                items(lessonList){
                        lesson -> LessonUi(lessonCategories = lesson, onClick = {})
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}