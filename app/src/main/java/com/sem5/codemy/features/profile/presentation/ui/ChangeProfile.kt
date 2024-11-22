package com.sem5.codemy.features.profile.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.profile.presentation.components.ProfileButton
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.montserratFontFamily
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable
fun ChangeProfile(navController: NavController){
    Scaffold(
        topBar = {
            TopBar(
                title = "Edit Profile",
                actions = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(17.dp, 0.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(30.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Change Profile",
                        fontSize = 20.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = ""    ,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = {
//                            "" = it
                        },
                        label = {
                            Text(
                                text = "Username",
                                fontSize = 12.sp,
                                fontFamily = publicSansFontFamily,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    ProfileButton(
                        label = {"Change Profile"},
                        onClick = {navController.navigate("profile")}
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }
    }
}