package com.sem5.codemy.features.screens.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.features.components.BottomBar
import com.sem5.codemy.R
import com.sem5.codemy.features.components.TopBar
import com.sem5.codemy.ui.theme.DarkBlue


@Composable
fun LearnHomePage(modifier: Modifier = Modifier, navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            TopBar(
                title = "Hello 👋 , Fridolin Austin",
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
        Modifier.padding(innerPadding)
        DisplayLessonData()
    }
}

@Composable
fun DisplayLessonData(){
    val lessonList = listOf(
        LessonData(R.drawable.displaylesson, "Pengantar Pemrograman"),
        LessonData(R.drawable.displaylesson, "Dasar Pemrograman"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut")
    )

    LazyColumn(modifier = Modifier.fillMaxHeight().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(lessonList){
                lesson -> LessonUi(lessonData = lesson)
            }
       }
}
