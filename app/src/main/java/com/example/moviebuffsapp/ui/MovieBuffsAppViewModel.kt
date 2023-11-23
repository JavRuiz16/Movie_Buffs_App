package com.example.moviebuffsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MovieBuffsAppViewModel {
    var moviebuffsappUiState: String by mutableStateOf("")
        private set


    init {
        movieBuffsAppPhotos()
        moviebuffsappUiState = "set the MovieBuffsApp API status response here!"
    }
}