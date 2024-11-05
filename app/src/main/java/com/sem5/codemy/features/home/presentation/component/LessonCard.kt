package com.sem5.codemy.features.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import com.sem5.codemy.ui.theme.montserratFontFamily

@Composable
fun LessonCard(
    lessonCategories: LessonCategories,
    modifier: Modifier,
    onClick: () -> Unit
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = {
            onClick()
        }
    ){
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .width(180.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Image(
                painter = painterResource(id = lessonCategories.lessonThumbnail),
                contentDescription = "html",
                modifier = Modifier.size(36.dp, 36.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(id = lessonCategories.lessonTitle),
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

    }
}

//@Preview
//@Composable
//fun LessonCardPreview(){
//
//    val lessonCategories = listOf(
//        LessonCategories(R.drawable.html, R.string.html),
//        LessonCategories(R.drawable.css, R.string.css),
//        LessonCategories(R.drawable.js, R.string.js),
//        LessonCategories(R.drawable.c, R.string.c),
//        LessonCategories(R.drawable.java, R.string.java),
//        LessonCategories(R.drawable.python, R.string.python)
//    )
//    LazyRow (
//        horizontalArrangement = Arrangement.spacedBy(24.dp)
//    ){
//        items(lessonCategories){ item ->
//            LessonCard(
//                lessonCategories = item,
//                modifier = Modifier ,
//                onClick = {}
//            )
//        }
//    }
//}