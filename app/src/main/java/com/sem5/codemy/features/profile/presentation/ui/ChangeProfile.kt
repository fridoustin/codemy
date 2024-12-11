package com.sem5.codemy.features.profile.presentation.ui

import android.util.Log
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.features.profile.presentation.components.ProfileButton
import com.sem5.codemy.ui.theme.components.TopBar
import com.sem5.codemy.ui.theme.montserratFontFamily
import com.sem5.codemy.ui.theme.publicSansFontFamily
import androidx.compose.runtime.livedata.observeAsState
import com.google.firebase.auth.FirebaseAuth
import com.sem5.codemy.features.profile.data.remote.getUserDocument

@Composable
fun ChangeProfile(navController: NavController, authViewModel: AuthView) {
    val authState = authViewModel.authState.observeAsState()
    val userName = remember { mutableStateOf<String?>(null) }
    val userEmail = remember { mutableStateOf<String?>(null) }
    val profilePhotoUrl = remember { mutableStateOf<String?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(Unit) {
        if (userId != null) {
            try {
                isLoading.value = true
                val userDocument = getUserDocument(userId)
                userName.value = userDocument?.getString("name")
                userEmail.value = userDocument?.getString("email")
                profilePhotoUrl.value = userDocument?.getString("photo")
            } catch (e: Exception) {
                Log.e("Profile", "Error fetching user data: ", e)
            } finally {
                isLoading.value = false
            }
        } else {
            isLoading.value = false
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Edit Profile",
                actions = {}
            )
        }
    ) { innerPadding ->
        if (isLoading.value) {
            // Jika ingin tampilkan loading indicator
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEFF4FA))
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
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
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Change Profile",
                            fontSize = 20.sp,
                            fontFamily = montserratFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Input untuk username
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = userName.value ?: "",
                            shape = RoundedCornerShape(5.dp),
                            onValueChange = {
                                userName.value = it
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

                        // Input untuk photo URL
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = profilePhotoUrl.value ?: "",
                            shape = RoundedCornerShape(5.dp),
                            onValueChange = {
                                profilePhotoUrl.value = it
                            },
                            label = {
                                Text(
                                    text = "Photo URL",
                                    fontSize = 12.sp,
                                    fontFamily = publicSansFontFamily,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        ProfileButton(
                            label = { "Change Profile" },
                            onClick = {
                                authViewModel.updateNameInFirestore(userName.value ?: "User")
                                profilePhotoUrl.value?.let {
                                    authViewModel.updatePhotoInFirestore(
                                        it
                                    )
                                }
                                navController.navigate("profile")
                            }
                        )

                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
            }
        }
    }
}
