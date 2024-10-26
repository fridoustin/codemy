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
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.R
import com.sem5.codemy.features.learn.data.LessonData
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue
import com.sem5.codemy.ui.theme.components.SearchBar


@Composable
fun LearnHomePage(modifier: Modifier = Modifier, navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val lessonList = listOf(
        LessonData(R.drawable.displaylesson, "Pengantar Pemrograman"),
        LessonData(R.drawable.displaylesson, "Dasar Pemrograman"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut")
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
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            item{
                SearchBar(modifier = Modifier)
            }

            items(lessonList){
                lesson -> LessonUi(lessonData = lesson)
            }
        }
    }
}

//@Composable
//fun DisplayLessonData(){
//    val lessonList = listOf(
//        LessonData(R.drawable.displaylesson, "Pengantar Pemrograman"),
//        LessonData(R.drawable.displaylesson, "Dasar Pemrograman"),
//        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
//        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
//        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
//        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut"),
//        LessonData(R.drawable.displaylesson, "Pemrograman Lanjut")
//    )
//
//    LazyColumn(modifier = Modifier.fillMaxHeight().padding(20.dp),
//        verticalArrangement = Arrangement.spacedBy(20.dp)
//    ) {
//        items(lessonList){
//                lesson -> LessonUi(lessonData = lesson)
//            }
//       }
//}

