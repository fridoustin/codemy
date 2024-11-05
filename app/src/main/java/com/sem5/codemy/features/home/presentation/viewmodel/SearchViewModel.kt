package com.sem5.codemy.features.home.presentation.viewmodel

import android.app.appsearch.SearchResult
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sem5.codemy.R
import com.sem5.codemy.features.home.data.LessonCategories
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel(private val context: Context): ViewModel(){

    private val _searchText = MutableStateFlow("")
    val searchText =  _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _lessons = MutableStateFlow(allLessons)
    @OptIn(FlowPreview::class)
    val lessons = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_lessons){ text, lessons ->
            if(text.isBlank()){
                lessons
            }else{
                delay(2000L)
                lessons.filter {
                    it.doesMatchSearchQuery(text, context)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _lessons.value
        )
    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}

private val allLessons = listOf(
    LessonCategories(R.drawable.c, R.string.c),
    LessonCategories(R.drawable.java, R.string.java),
    LessonCategories(R.drawable.js, R.string.js),
    LessonCategories(R.drawable.python, R.string.python),
    LessonCategories(R.drawable.css, R.string.css),
    LessonCategories(R.mipmap.cpimg, R.string.cplong),
    LessonCategories(R.drawable.web, R.string.webprog, "webprogramming"),
    LessonCategories(R.drawable.cyberimg, R.string.cyber),
    LessonCategories(R.drawable.gameimg, R.string.game),
    LessonCategories(R.drawable.html, R.string.html),
)