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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sem5.codemy.auth.HomePage
import com.sem5.codemy.challenge.ChallengeHomePage
import com.sem5.codemy.learn.LearnHomePage
import com.sem5.codemy.other.Other
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

@Composable
fun MainScreen(modifier: Modifier) {
    val navBotList = listOf(
        NavItem("Home", Icons.Default.Home, "home"),
        NavItem("Learn", Icons.Default.School, "learnhome"),
        NavItem("Challenge", Icons.Default.Flag, "challengehome"),
        NavItem("Other", Icons.Default.MoreHoriz, "other")
    )

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                containerColor = BlueNormal,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),

                ) {
                navBotList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = "Icon",
                                tint = if (selectedIndex == index) DarkBlue else LightBlue
                            )
                        },
                        label = {
                            Text(
                                text = navItem.label,
                                color = if (selectedIndex == index) DarkBlue else LightBlue
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = DarkBlue,
                            unselectedIconColor = LightBlue,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navController = rememberNavController())
    }
}

@Composable
fun ContentScreen(modifier: Modifier, selectedIndex : Int, navController: NavController){
    when(selectedIndex){
        0 -> HomePage(modifier = Modifier, navController = navController, authViewModel = AuthView())
        1 -> LearnHomePage(modifier = Modifier, navController = navController)
        2 -> ChallengeHomePage(modifier = Modifier, navController = navController)
        3 -> Other(modifier = Modifier, navController = navController)
    }
}