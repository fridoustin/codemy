package com.sem5.codemy.features.screens.challenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.DarkBlue


@Composable
fun ChallengeHomePage(modifier: Modifier = Modifier, navController: NavController){
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "This is the temporary Challenge Page",
                fontSize = 32.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}