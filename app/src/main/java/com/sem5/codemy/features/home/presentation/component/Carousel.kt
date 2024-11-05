package com.sem5.codemy.features.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.NewsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Carousel(newsData: List<NewsData>){
    val carouselState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var currentIndex by remember {mutableStateOf(0)}

    LaunchedEffect(carouselState){
        while (true) {
            delay(2500)
            currentIndex = (currentIndex + 1) % newsData.size
            coroutineScope.launch {
                carouselState.animateScrollToItem(currentIndex)
            }
        }
    }

    LazyRow(
        state = carouselState,
        modifier = Modifier
            .padding(
                top = 20.dp,
                start = 26.dp,
                end = 26.dp
            )
            .aspectRatio(16/9f),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(newsData){ item ->
            Image(
                painter = painterResource(id = item.newsThumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16/9f)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }
    }
}

@Preview
@Composable
fun CarouselPreview() {
    val newsData = listOf(
        NewsData(R.drawable.news1),
        NewsData(R.drawable.news2),
        NewsData(R.drawable.news3),
    )
    Carousel(newsData = newsData)
}