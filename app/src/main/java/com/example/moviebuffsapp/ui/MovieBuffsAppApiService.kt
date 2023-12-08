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
    abstract val isShowingListPage: Boolean

    data class Success(val photos: String) : MovieBuffsAppUiState {
        override val isShowingListPage: Boolean
            get() = TODO("Not yet implemented")
    }

    object Error : MovieBuffsAppUiState {
        override val isShowingListPage: Boolean
            get() = TODO("Not yet implemented")
    }

    object Loading : MovieBuffsAppUiState {
        override val isShowingListPage: Boolean
            get() = TODO("Not yet implemented")
    }
}

interface MovieBuffsAppApiService {
    @GET("MovieBuffsApp/photos.json")
    suspend fun getPhotos(): List<MovieBuffsApp>
}
class MovieBuffsAppViewModel : ViewModel() {
    var moviebuffsappUiState: MovieBuffsAppUiState by mutableStateOf(MovieBuffsAppUiState.Loading)
        private set
    init {
        getMovieBuffsApp()
    }

    private fun getMovieBuffsApp() {
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