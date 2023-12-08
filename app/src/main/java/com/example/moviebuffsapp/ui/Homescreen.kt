package com.example.moviebuffsapp.ui

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.R
import com.example.moviebuffsapp.ui.theme.MovieBuffsAppTheme
import com.example.moviebuffsapp.ui.theme.Network.MovieBuffsApp


@Composable
fun HomeScreen(
    movieBuffsAppUiState: MovieBuffsAppUiState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MovieBuffsAppBar(
                isShowingListPage = movieBuffsAppUiState.isShowingListPage,
                onBackButtonClick = { /* handle back button click */ },
            )
        }
    ) { innerPadding ->
        when (movieBuffsAppUiState) {
            is MovieBuffsAppUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is MovieBuffsAppUiState.Success -> {
                if (movieBuffsAppUiState.isShowingListPage) {
                    MovieBuffsAppList(
                        moviebuffsappList = movieBuffsAppUiState.moviebuffsappList,
                        onClick = { movieBuffsApp ->
                            /* Update ViewModel and navigate */
                        },
                        contentPadding = contentPadding,
                        modifier = Modifier.padding(innerPadding)
                    )
                } else {
                    MovieBuffsAppDetail(
                        selectedMovieBuffsApp = movieBuffsAppUiState.currentMovieBuffsApp,
                        contentPadding = innerPadding,
                        onBackPressed = { /* Handle back navigation */ }
                    )
                }
            }
            is MovieBuffsAppUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun MovieBuffsAppList(
    moviebuffsappList: List<MovieBuffsApp>,
    onClick: (MovieBuffsApp) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(moviebuffsappList) { moviebuffsapp ->
            MovieBuffsAppCard(
                moviebuffsapp = moviebuffsapp,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                onClick = { onClick(moviebuffsapp) }
            )
        }
    }
}

@Composable
fun MovieBuffsAppCard(moviebuffsapp: MovieBuffsApp, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevation(defaultElevation = 8.dp)
    ) {
        // Display movie card content
    }
}

@Composable
private fun MovieBuffsAppDetail(
    selectedMovieBuffsApp: MovieBuffsApp,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }

    Column (
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
            .padding(contentPadding)
    ) {
        Box{
            Image(
                painter = painterResource(selectedMovieBuffsApp.moviebuffsappImageBanenr),

        }

    }
    }


// Other Composables remain unchanged...

@Preview
@Composable
fun HomeScreenPreview() {
    val movieBuffsAppUiState = MovieBuffsAppUiState.Success(
        isShowingListPage = true,
        moviebuffsappList = /* Populate with sample movie data */
    )
    val padding = PaddingValues(16.dp)

    HomeScreen(
        movieBuffsAppUiState = movieBuffsAppUiState,
        retryAction = {},
        contentPadding = padding
    )
}
