package com.sem5.codemy.features.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue
import com.sem5.codemy.ui.theme.components.SearchBar


@Composable
fun LearnHomePage(modifier: Modifier = Modifier, navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val lessonList = listOf(
        LessonCategories(R.drawable.cpimg, R.string.cplong),
        LessonCategories(R.drawable.web, R.string.webprog, "webprogramming"),
        LessonCategories(R.drawable.cyberimg, R.string.cyber),
        LessonCategories(R.drawable.gameimg, R.string.game)
    )

    Scaffold(
        containerColor = LightBlue,
        topBar = {
            TopBar(
                title = "Ingin belajar apa hari ini?",
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding),
        ){
            SearchBar(navController)

            LazyColumn(){
                item{
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }

                items(lessonList){
                        lesson -> LessonUi(
                            lessonCategories = lesson,
                            onClick = {
                                lesson.route?.let { navController.navigate(it) }
                            }
                        )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

