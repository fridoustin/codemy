package com.sem5.codemy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sem5.codemy.auth.HomePage
import com.sem5.codemy.auth.SignIn
import com.sem5.codemy.auth.SignUp
import com.sem5.codemy.challenge.ChallengeHomePage
import com.sem5.codemy.learn.LearnHomePage
import com.sem5.codemy.other.Other
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

@Composable
fun NavigationApp(modifier: Modifier = Modifier, authViewModel: AuthView) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainscreen", builder = {
        composable("mainscreen"){
            MainScreen(modifier)
        }
        composable("signin"){
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
            Other(modifier, navController)
        }

    })
}

data class NavItem(
    val label : String,
    val icon : ImageVector,
    val route : String
)

