package com.sem5.codemy.features.profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.features.auth.presentation.viewmodel.AuthView
import com.sem5.codemy.ui.theme.montserratFontFamily
import com.sem5.codemy.ui.theme.publicSansFontFamily


@Composable
fun ProfileCard(
    userName: () -> String,
    userEmail: () -> String
){
    Card(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp, top = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 16.dp),
            text = "Profile Info",
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 32.dp),

        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Row(

            ){
                Text(
                    text = "Nama : ",
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = userName(),
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(

            ){
                Text(
                    text = "Email : ",
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = userEmail(),
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}