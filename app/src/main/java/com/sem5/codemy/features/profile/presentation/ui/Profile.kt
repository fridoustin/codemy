package com.sem5.codemy.features.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.features.profile.presentation.components.LogoutButton
import com.sem5.codemy.features.profile.presentation.components.ProfileCard
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.publicSansFontFamily
import kotlinx.coroutines.launch


@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthView){
    val authState = authViewModel.authState.observeAsState()
    val userName = authViewModel.userName.observeAsState()
    val userEmail = authViewModel.userEmail.observeAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val coroutineScope = rememberCoroutineScope()
    var imageUri by remember {mutableStateOf<Uri?>(null)}
    var profileImage by remember { mutableStateOf<String?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ){
        uri : Uri? ->
        imageUri = uri
    }

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("signin")
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Profile",
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
                            tint = DarkBlue
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                currentScreen = currentRoute ?: "",
                navController = navController,
                onItemSelected = { selectedScreen ->
                    if (selectedScreen != currentRoute) {
                        navController.navigate(selectedScreen)
                    }
                }
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFF4FA))
                .padding(innerPadding),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            if(profileImage != null){
//                AsyncImage(
//                    model = profileImage,
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier.size(100.dp)
//                )
//            } else {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "Default Profile Picture",
//                    modifier = Modifier.size(100.dp),
//                    tint = Color.Gray
//                )
//            }
//
//
//
//            LaunchedEffect(imageUri) {
//                imageUri?.let { uri ->
//                    coroutineScope.launch {
//                        uploadImageToFirebase(uri,
//                            onSuccess = { downloadUrl ->
//                                profileImage = downloadUrl
//                            },
//                            onFailure = { e ->
//                                println("Error uploading image: ${e.message}")
//                            }
//                        )
//                    }
//                }
//            }

            ProfileCard(
                userName = {userName.value ?: ""},
                userEmail = {userEmail.value ?: ""}
            )

//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        start = 26.dp,
//                        end = 26.dp,
//                    ),
//                colors = ButtonDefaults.buttonColors(Color(0xFF628ECB)),
//                shape = RoundedCornerShape(16.dp),
//                onClick = {imagePickerLauncher.launch("image/*")}
////                onClick = {
////                    isUpload.value = true
////                    bitmap.value.let {bitmap ->
////                        uploadImageToFirebase()
////                    }
////                }
//            ){
//                Text(
//                    text = "Change Photo",
//                    fontFamily = publicSansFontFamily,
//                    fontSize = 12.sp,
//                    color = Color(0xFFF0F3FA),
//                    fontWeight = FontWeight.Medium
//                )
//            }

            LogoutButton { authViewModel.signOut(navController) }
        }
    }
}