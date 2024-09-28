package com.sem5.codemy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sem5.codemy.auth.HomePage
import com.sem5.codemy.auth.SignIn
import com.sem5.codemy.auth.SignUp
import com.sem5.codemy.challenge.ChallengeHomePage
import com.sem5.codemy.learn.LearnHomePage
import com.sem5.codemy.other.Other

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
        composable("learnhome"){
            LearnHomePage(modifier,navController)
        }
        composable("challengehome"){
            ChallengeHomePage(modifier, navController)
        }
        composable("other"){
            Other(modifier, navController)
        }
    })
}

data class NavItem(
    val label : String,
    val icon : ImageVector,
    val route : String
)