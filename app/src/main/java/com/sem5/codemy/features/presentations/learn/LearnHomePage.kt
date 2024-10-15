package com.sem5.codemy.features.presentations.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.features.components.BottomBar
import com.sem5.codemy.R


@Composable
fun LearnHomePage(modifier: Modifier = Modifier, navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
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

