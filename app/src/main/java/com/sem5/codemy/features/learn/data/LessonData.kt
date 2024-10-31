package com.sem5.codemy.features.learn.data

import androidx.annotation.DrawableRes

data class LessonData(
    @DrawableRes val thumbnail: Int,
    val lessonTitle: String,
    val route : String? = null
)