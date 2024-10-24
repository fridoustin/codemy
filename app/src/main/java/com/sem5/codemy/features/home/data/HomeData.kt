package com.sem5.codemy.features.home.data

import androidx.annotation.DrawableRes

data class NewsData(
    @DrawableRes val newsThumbnail: Int
)

data class LessonCategories(
    @DrawableRes val lessonThumbnail: Int,
    val lessonTitle: Int
)