package com.sem5.codemy.features.screens.learn

import androidx.annotation.DrawableRes

data class LessonData(
    @DrawableRes val thumbnail: Int,
    val lessonTitle: String
)