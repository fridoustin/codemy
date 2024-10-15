package com.sem5.codemy.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sem5.codemy.features.presentations.auth.AuthState
import com.sem5.codemy.features.presentations.auth.AuthView
import com.sem5.codemy.features.presentations.auth.SignIn
import com.sem5.codemy.features.presentations.auth.SignUp
import com.sem5.codemy.features.presentations.challenge.ChallengeHomePage
import com.sem5.codemy.features.presentations.home.HomePage
import com.sem5.codemy.features.presentations.learn.LearnHomePage
import com.sem5.codemy.features.presentations.other.Other

@Composable
fun NavigationApp(modifier: Modifier = Modifier, authViewModel: AuthView) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState()

    val startDestination = when(authState) {
        is AuthState.Authenticated -> "home"
        else -> "signin"
    }

    NavHost(navController = navController, startDestination = startDestination, builder = {
        composable("signin") {
            SignIn(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignUp(modifier, navController, authViewModel)
        }
        composable("home"){
            HomePage(modifier, navController, authViewModel)
        }
        composable("learnhome") {
            LearnHomePage(modifier, navController)
        }
        composable("challengehome"){
            ChallengeHomePage(modifier, navController)
        }
        composable("other"){
            Other(modifier, navController, authViewModel)
        }
//        composable("bottombar"){
//            BottomBar(modifier, navController, authViewModel)
//        }

    })
}


