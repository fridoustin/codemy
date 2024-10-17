package com.sem5.codemy.features.screens.learn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable

fun LessonUi(lessonData: LessonData){
    Card(modifier = Modifier
        .padding(26.dp,19.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
    ){
         Column(modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
             horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = lessonData.thumbnail), contentDescription = null,
                modifier = Modifier.size(40.dp))

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp))
            {
                Text(
                    text = lessonData.lessonTitle,
                    color = Color.Black,
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        }
    }
}