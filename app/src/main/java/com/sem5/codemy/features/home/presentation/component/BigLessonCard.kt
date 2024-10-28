package com.sem5.codemy.features.home.presentation.component


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.ui.theme.publicSansFontFamily
import androidx.compose.ui.Modifier

@Composable
fun BigLessonCard(

){
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White),
    ){
        Text(
            text = "Lihat lainnya",
            fontFamily = publicSansFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        )
    }
}


@Preview
@Composable
fun PreviewCard(){
    BigLessonCard()
}