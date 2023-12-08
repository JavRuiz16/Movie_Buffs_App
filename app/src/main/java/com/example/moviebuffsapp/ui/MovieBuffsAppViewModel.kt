package com.example.moviebuffsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsAppApiService
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsApp
import kotlinx.coroutines.launch
import retrofit2.http.GET
import java.io.IOException


sealed interface MovieBuffsAppUiState {
    data class Success(val photos: String) : MovieBuffsAppUiState
    object Error : MovieBuffsAppUiState
    object Loading : MovieBuffsAppUiState
}

interface MovieBuffsAppApiService {
    @GET("MovieBuffsApp/photos.json")
    suspend fun getPhotos(): List<MovieBuffsApp>
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
                MovieBuffsAppUiState.Success(MovieBuffsAppApiService.retrofitService.getPhotos())
            } catch (e: IOException) {
                MovieBuffsAppUiState.Error
            }
            MovieBuffsAppUiState.Success("Success: ${listResult.size} Movie Buffs App photos retrieved")
        }
    }
}