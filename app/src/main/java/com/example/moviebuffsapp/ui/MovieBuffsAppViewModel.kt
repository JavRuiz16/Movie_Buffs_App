package com.example.moviebuffsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsAppApi
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsAppPhoto
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MovieBuffsAppUiState {
    data class Success(val photos: List<MovieBuffsAppPhoto>) : MovieBuffsAppUiState
    object Error : MovieBuffsAppUiState
    object Loading : MovieBuffsAppUiState
}

class MovieBuffsAppViewModel : ViewModel() {
    var moviebuffsappUiState: MovieBuffsAppUiState by mutableStateOf(MovieBuffsAppUiState.Loading)
        private set
    init {
        getMovieBuffsAppPhotos()
    }

    fun getMovieBuffsAppPhotos() {
        viewModelScope.launch {
            moviebuffsappUiState = try {
                MovieBuffsAppUiState.Success(MovieBuffsAppApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                MovieBuffsAppUiState.Error
            }
        }
    }
}