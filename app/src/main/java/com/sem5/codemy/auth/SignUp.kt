package com.sem5.codemy.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.AuthState
import com.sem5.codemy.AuthView
import com.sem5.codemy.R
import com.sem5.codemy.ui.theme.montserratFontFamily
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable
fun SignUp(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthView){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF395886)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.codemylogo_putih),
            contentDescription = "logo",
            modifier = Modifier.size(100.dp, 100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Code better with C/odemy>",
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.White
        )

        Card(
            modifier = Modifier
                .padding(17.dp, 40.dp),
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
                    text = "Selamat Datang !",
                    fontSize = 20.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    shape = RoundedCornerShape(5.dp),
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(
                            text = "Email",
                            fontSize = 12.sp,
                            fontFamily = publicSansFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    shape = RoundedCornerShape(5.dp),
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text(
                            text = "Password",
                            fontSize = 12.sp,
                            fontFamily = publicSansFontFamily,
                            fontWeight = FontWeight.Medium,
                            )
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF628ECB)),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        authViewModel.signUp(email, password)
                    },
                    enabled = authState.value != AuthState.Loading
                    ) {
                    Text(
                        text = "Create Account",
                        fontFamily = publicSansFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color(0xFFF0F3FA)
                    )
                }
            }

        }

    }
}