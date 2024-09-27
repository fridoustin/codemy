package com.sem5.codemy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sem5.codemy.auth.HomePage
import com.sem5.codemy.auth.SignIn
import com.sem5.codemy.auth.SignUp

@Composable
fun NavigationApp(modifier: Modifier = Modifier, authViewModel: AuthView) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signin", builder = {
        composable("signin"){
            SignIn(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignUp(modifier, navController, authViewModel)
        }
        composable("home"){
            HomePage(modifier, navController, authViewModel)
        }
    })
}