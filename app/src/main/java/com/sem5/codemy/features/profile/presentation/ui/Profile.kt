package com.sem5.codemy.features.profile.presentation.ui

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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.layout.ContentScale
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
import com.google.firebase.firestore.FirebaseFirestore
import com.sem5.codemy.features.auth.data.User
import com.sem5.codemy.ui.theme.components.BottomBar
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthState
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.features.profile.data.remote.getUserProfile
import com.sem5.codemy.features.profile.presentation.components.LogoutButton
import com.sem5.codemy.features.profile.presentation.components.ProfileCard
import com.sem5.codemy.features.profile.presentation.viewmodel.ProfileViewModel
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.publicSansFontFamily
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthView,
){
    val authState = authViewModel.authState.observeAsState()
    val userName = authViewModel.userName.observeAsState()
    val userEmail = authViewModel.userEmail.observeAsState()
    var userPhoto = authViewModel.userPhoto.observeAsState()
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                        onClick = {navController.navigate("notification")},
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            if(userPhoto != null){
//                Image(
//                    painter = rememberImagePainter(userPhoto),
//                    contentDescription = "Profile Picture",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(100.dp)
//                )
//            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                )
//            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileCard(
                userName = {userName.value ?: ""},
                userEmail = {userEmail.value ?: ""}
            )

            LogoutButton { authViewModel.signOut() }
        }
    }
}

suspend fun getProfilePhotoUrl(userId: String): String? {
    val db = FirebaseFirestore.getInstance()
    val document = db.collection("users").document(userId).get().await()
    return document.getString("profilePhotoUrl")
}