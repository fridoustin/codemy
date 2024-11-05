package com.sem5.codemy.features.home.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.montserratFontFamily
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable
fun SmallCard(
    lessonCategories: LessonCategories,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp), clip = false)
    ){
        Card(
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color.White),
//            elevation = CardDefaults.cardElevation(1.dp),
//            border = BorderStroke(1.dp, color = DarkBlue)
        ){
            Column(
                modifier = Modifier
                    .width(135.dp)
                    .height(135.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Image(
                painter = painterResource(id = lessonCategories.lessonThumbnail),
//                    painter = painterResource(id = R.drawable.web),
                    contentDescription = "html",
                    modifier = Modifier.size(90.dp, 90.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                text = stringResource(id = lessonCategories.lessonTitle),
//                    text = "Web Developer",
                    fontFamily = publicSansFontFamily,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    }


//@Preview
//@Composable
//fun PreviewSmallCard(){
//
//  SmallCard(LessonCategories(R.drawable.cpimg, R.string.cp))
//
//}