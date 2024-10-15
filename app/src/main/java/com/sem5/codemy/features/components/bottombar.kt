package com.sem5.codemy.features.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

//@Composable
//fun BottomBar(modifier: Modifier, navController: NavController, authView: AuthView) {
//    val navBotList = listOf(
//        NavItem("Home", Icons.Default.Home),
//        NavItem("Learn", Icons.Default.School),
//        NavItem("Challenge", Icons.Default.Flag),
//        NavItem("Other", Icons.Default.MoreHoriz)
//    )
//
//    var selectedIndex by remember {
//        mutableStateOf(0)
//    }
//
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize(),
//        bottomBar = {
//            BottomAppBar(
//                containerColor = BlueNormal,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
//
//                ) {
//                navBotList.forEachIndexed { index, navItem ->
//                    NavigationBarItem(
//                        selected = selectedIndex == index,
//                        onClick = {
//                            selectedIndex = index
//                        },
//                        icon = {
//                            Icon(
//                                imageVector = navItem.icon,
//                                contentDescription = "Icon",
//                                tint = if (selectedIndex == index) DarkBlue else LightBlue
//                            )
//                        },
//                        label = {
//                            Text(
//                                text = navItem.label,
//                                color = if (selectedIndex == index) DarkBlue else LightBlue
//                            )
//                        },
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = DarkBlue,
//                            unselectedIconColor = LightBlue,
//                            indicatorColor = Color.Transparent
//                        )
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navController = navController, authView = authView)
//    }
//}
//
//@Composable
//fun ContentScreen(modifier: Modifier, selectedIndex : Int, navController: NavController, authView: AuthView){
//    when(selectedIndex){
//        0 -> HomePage(modifier = Modifier, navController = navController, authViewModel = authView )
//        1 -> LearnHomePage(modifier = Modifier, navController = navController)
//        2 -> ChallengeHomePage(modifier = Modifier, navController = navController)
//        3 -> Other(modifier = Modifier, navController = navController, authViewModel = authView)
//    }
//}

@Composable
fun BottomBar(
    currentScreen: String,
    navController: NavController,
    onItemSelected: (String) -> Unit
){
    BottomAppBar(
        containerColor = BlueNormal,
        contentColor = LightBlue,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ){
        val items = listOf(
            BottomBarItem("Home", Icons.Default.Home, "home"),
            BottomBarItem("Learn", Icons.Default.School, "learnhome"),
            BottomBarItem("Challenge", Icons.Default.Flag, "challengehome"),
            BottomBarItem("Other", Icons.Default.MoreHoriz, "other")
        )

        items.forEach{ item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                selected = currentScreen == item.route,
                onClick = {
                    if(navController.currentDestination?.route != item.route){
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    onItemSelected(item.route)
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkBlue,
                    unselectedIconColor = LightBlue,
                    selectedTextColor = DarkBlue,
                    unselectedTextColor = LightBlue,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomBarItem(
    val label : String,
    val icon : ImageVector,
    val route : String
)