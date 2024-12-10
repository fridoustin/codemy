package com.sem5.codemy.features.profile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.ui.theme.publicSansFontFamily

@Composable
fun ProfileButton(
    label: () -> String,
    onClick: () -> Unit
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 26.dp,
                end = 26.dp,
            ),
        colors = ButtonDefaults.buttonColors(Color(0xFF628ECB)),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick()
        },
    ) {
        Text(
            text = label(),
            fontFamily = publicSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color(0xFFF0F3FA)
        )
    }
}