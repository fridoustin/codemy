package com.sem5.codemy.features.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.features.home.presentation.viewmodel.SearchViewModel
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.publicSansFontFamily
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.sem5.codemy.ui.theme.DarkBlue
import com.sem5.codemy.ui.theme.components.TopBar

@Composable
fun SearchScreen(viewModel: SearchViewModel){
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val lessons by viewModel.lessons.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Search",
                actions = {
                    IconButton(
                        onClick = {},
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
    ){innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueNormal)
                .padding(innerPadding)
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 26.dp,
                            end = 26.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    value = searchText,
                    onValueChange = viewModel::onSearchTextChange,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Apa yang kamu cari?",
                            fontFamily = publicSansFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                    }
                )
                if(isSearching){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ){
                        items(lessons.size){ index ->
                            val lesson = lessons[index]
                            Text(
                                text = stringResource(id = lesson.lessonTitle),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}