package com.sem5.codemy.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sem5.codemy.features.article.presentation.ui.ArticleDetailPage
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.features.auth.presentation.ui.SignIn
import com.sem5.codemy.features.auth.presentation.ui.SignUp
import com.sem5.codemy.features.home.presentation.ui.NotificationPage
import com.sem5.codemy.features.home.presentation.ui.SearchScreen
import com.sem5.codemy.features.home.presentation.viewmodel.SearchViewModel
import com.sem5.codemy.features.learn.presentation.ui.WebProgrammingPage
import com.sem5.codemy.features.article.presentation.ui.ArticleHomePage
import com.sem5.codemy.features.screens.home.HomePage
import com.sem5.codemy.features.learn.presentation.ui.LearnHomePage
import com.sem5.codemy.features.profile.presentation.ui.ChangeProfile
import com.sem5.codemy.features.profile.presentation.ui.Profile

@Composable
fun NavigationApp(modifier: Modifier = Modifier, authViewModel: AuthView) {
    val navController = rememberNavController()
    val searchViewModel = remember {SearchViewModel(context = navController.context)}
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
        composable("articlehome"){
            ArticleHomePage(modifier, navController)
        }
        composable(
            route = "articledetail/{articleId}",
            arguments = listOf(navArgument("articleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId") ?: ""
            ArticleDetailPage(articleId = articleId, navController = navController)
        }
        composable("profile"){
            Profile(modifier, navController, authViewModel)
        }
        composable("webprogramming"){
            WebProgrammingPage(modifier, navController)
        }
        composable("search"){
            SearchScreen(viewModel = searchViewModel, navController)
        }
        composable("notification"){
            NotificationPage(navController)
        }
        composable("changeprofile") {
            ChangeProfile(navController, authViewModel)
        }
    })
}


