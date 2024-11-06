package com.sem5.codemy.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sem5.codemy.features.home.presentation.viewmodel.SearchViewModel
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.publicSansFontFamily
import kotlin.reflect.KFunction1

@Composable
fun SearchBar(
    navController: NavController
){

    val viewModel = SearchViewModel(context = navController.context)
    val searchText by viewModel.searchText.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueNormal)
            .clickable { navController.navigate("search") }
    ) {
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp, 10.dp)
                    .clickable { navController.navigate("search") }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    value = searchText,
                    onValueChange = {},
                    enabled = false,
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
                    },
                    readOnly = true
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun SearchBarPreview(){
//    SearchBar()
//}
