// File: ArticleRow.kt
package com.sem5.codemy.features.article.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sem5.codemy.features.article.presentation.ui.Article

@Composable
fun ArticleRow(article: Article, onReadMoreClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(210.dp) 
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Row untuk judul dan kategori
            // Row(
            //     modifier = Modifier.fillMaxWidth(),
            //     horizontalArrangement = Arrangement.SpaceBetween
            // ) {
                Text(
                    text = article.articleTitle, // Judul artikel
                    maxLines = 2,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                // Kategori di sebelah kanan judul, dengan ukuran teks lebih kecil
                Text(
                    text = article.category,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            // }

            // Divider untuk memisahkan judul/kategori dan deskripsi
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Deskripsi artikel
            Text(
                text = article.articleDescription,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tombol "Read More" di pojok kanan bawah
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onReadMoreClick,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Read More",
                        fontSize = 10.sp,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}