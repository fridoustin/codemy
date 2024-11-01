package com.sem5.codemy.features.home.presentation.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.ui.theme.publicSansFontFamily
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.ui.theme.montserratFontFamily

@Composable
fun BigLessonCard(
    navController: NavController
){
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp, bottom = 16.dp),
    ){

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                SmallCard(
                    LessonCategories(R.drawable.cpimg, R.string.cp),
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.width(32.dp))
                SmallCard(
                    LessonCategories(R.drawable.web, R.string.webprog),
                    onClick = {
                        navController.navigate("webprogramming")
                    }
                )
            }

            Row(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            ){
                SmallCard(
                    LessonCategories(R.drawable.cyberimg, R.string.cyber),
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.width(32.dp))
                SmallCard(
                    LessonCategories(R.drawable.gameimg, R.string.game),
                    onClick = {

                    }
                )
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewCard(){
//    BigLessonCard()
//}