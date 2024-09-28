package com.sem5.codemy.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.AuthState
import com.sem5.codemy.AuthView
import com.sem5.codemy.NavItem
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthView){
    val authState = authViewModel.authState.observeAsState()

    val navBotList = listOf(
        NavItem("Home", Icons.Default.Home, "home"),
        NavItem("Learn", Icons.Default.School, "learnhome"),
        NavItem("Challenge", Icons.Default.Flag, "challengehome"),
        NavItem("Other", Icons.Default.MoreHoriz, "other")
    )

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("signin")
            else -> Unit
        }
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
                            navController.navigate(navItem.route)
                        },
                        icon = {
                            Icon(imageVector = navItem.icon,
                                contentDescription = "Icon",
                                tint = if(selectedIndex == index) DarkBlue else LightBlue
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
    ){ innerPadding ->
        BottomBar(modifier = Modifier.padding(innerPadding))

        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEFF4FA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(
            text = "This is the temporary Home Page",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        TextButton(onClick = {
            authViewModel.signOut()
        }) {
            Text(text = "Sign Out")
        }
    }
    }


}

@Composable
fun BottomBar(modifier: Modifier){

}