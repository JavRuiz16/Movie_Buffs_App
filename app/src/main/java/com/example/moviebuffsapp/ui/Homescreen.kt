package com.example.moviebuffsapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResourcemoviebuffsappphotos
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviebuffsappphotos.R
import com.example.moviebuffsappphotos.ui.theme.MoviePhotosTheme

@Composable
fun HomeScreen(
    marsUiState: String, modifier: Modifier = Modifier
) {
    ResultScreen(marsUiState, modifier)
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MovieBuffsPhotoAppPhotosTheme {
        ResultScreen("Placeholder result text")
    }
}