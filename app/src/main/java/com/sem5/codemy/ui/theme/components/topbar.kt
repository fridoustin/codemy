package com.sem5.codemy.ui.theme.components

import android.app.Notification.Action
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
//    navController: NavController,

){
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BlueNormal
        ),
        actions = actions
    )
}

@Preview
@Composable
fun TopBarPreview(){
    TopBar(
        title = "Hello 👋 , Fridolin Austin",
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
                    tint = DarkBlue,
                )
            }
        }
        )
}