package com.sem5.codemy.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

@Composable
fun BottomBar(
    currentScreen: String,
    navController: NavController,
    onItemSelected: (String) -> Unit
){
    BottomAppBar(
        containerColor = BlueNormal,
        contentColor = LightBlue,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .height(80.dp)
    ){
        val items = listOf(
            BottomBarItem("Home", Icons.Default.Home, "home"),
            BottomBarItem("Learn", Icons.Default.School, "learnhome"),
            BottomBarItem("Article", Icons.Default.Article, "articlehome"),
            BottomBarItem("Profile", Icons.Default.Person, "profile")
        )

        items.forEach{ item ->
            NavigationBarItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                        Text(
                            text = item.label,
                            fontSize = 10.sp
                        )
                    }
                },
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

@Preview
@Composable
fun BottomBarPreview(){
    BottomBar(
        currentScreen = "home",
        navController = NavController(LocalContext.current),
        onItemSelected = {}
    )
}