package com.sem5.codemy.features.home.data

import android.content.Context
import androidx.annotation.DrawableRes

data class NewsData(
    @DrawableRes val newsThumbnail: Int
)

data class LessonCategories(
    @DrawableRes val lessonThumbnail: Int,
    val lessonTitle: Int,
    val route : String? = null
) {
    fun doesMatchSearchQuery(query: String, context: Context): Boolean {
        val lessonTitleString = context.getString(lessonTitle)
        return lessonTitleString.contains(query, ignoreCase = true)
        }
    }

    // data class Article(
    //     val articleTitle: Int,
    //     val articleContent:
    //     val route : String? = null
    // ) {
    //     fun doesMatchSearchQuery(query: String, context: Context): Boolean {
    //         val lessonTitleString = context.getString(lessonTitle)
    //         return lessonTitleString.contains(query, ignoreCase = true)
    //         }
    //     }