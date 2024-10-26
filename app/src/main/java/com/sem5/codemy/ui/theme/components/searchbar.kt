package com.sem5.codemy.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.ui.theme.BlueNormal
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable
fun SearchBar(
    modifier: Modifier
//    query: String,
//    onQueryChange: (String) -> Unit,
//    onSearch: () -> Unit,
//    active: Boolean,
//    onActiveChange: (Boolean) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueNormal)
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
                value = "",
                onValueChange = {},
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
        }
    }
}

@Preview
@Composable
fun SearchBarPreview(){
    SearchBar(modifier = Modifier)
}
