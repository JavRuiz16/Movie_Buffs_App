package com.example.moviebuffsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsAppPhoto
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MovieBuffsAppUiState {
    data class Success(val photos: String) : MovieBuffsAppUiState
    data object Error : MovieBuffsAppUiState
    data object Loading : MovieBuffsAppUiState
}

fun getMovieBuffsAppPhotos() {
    val viewModelScope
    viewModelScope.launch {
        val listResult = MovieBuffsAppApi.retrofitService.getPhotos()
        moviebuffsappUiState = (listResult)

    }

    viewModelScope.launch {
        moviebuffsappUiState = try {
            val listResult = moviebuffsappApi.retrofitService.getPhotos()
            MovieBuffsAppUiState.Success(listResult)
        } catch (e: IOException) {
            MovieBuffsAppUiState.Error
        }
    }

    class MovieBuffsAppViewModel {
        var moviebuffsappUiState: String by mutableStateOf("")
            private set

        init {
            MovieBuffsAppPhoto()
            moviebuffsappUiState = "set the MovieBuffsApp API status response here!"
        }
    }
}