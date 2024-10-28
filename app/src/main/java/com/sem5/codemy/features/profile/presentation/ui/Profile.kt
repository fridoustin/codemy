package com.sem5.codemy.features.screens.profile

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.features.profile.presentation.components.LogoutButton
import com.sem5.codemy.features.profile.presentation.components.ProfileCard
import com.sem5.codemy.ui.theme.DarkBlue


@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthView){
    val authState = authViewModel.authState.observeAsState()
    val userName = authViewModel.userName.observeAsState()
    val userEmail = authViewModel.userEmail.observeAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("signin")
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Profile",
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
    ){innerPadding -> Modifier.padding(innerPadding)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "This is the temporary Other Page",
                fontSize = 32.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )

            ProfileCard(
                userName = {userName.value ?: "User"},
                userEmail = {userEmail.value ?: "None"}
            )

            LogoutButton { authViewModel.signOut(navController) }
        }
    }
}