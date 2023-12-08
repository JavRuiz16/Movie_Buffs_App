package com.example.moviebuffsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marsphotos.R
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
                        contentPadding = contentPadding,
                        modifier = Modifier.padding(innerPadding)
                    )
                } else {
                    //
                    // TODO: Implement the Details page
                }
            }
            is MovieBuffsAppUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun MovieBuffsAppList(
    moviebuffsappList: List<MovieBuffsApp>,
    OnClick:(MovieBuffsApp) -> Unit,
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
                modifier = modifier,
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun MovieBuffsAppCard(photo: Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.moviephotosapp_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun PhotosGridScreen(photos: List<com.example.moviebuffsapp.ui.theme.Network.MovieBuffsApp>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            MovieBuffsAppPCard(
                photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBuffsAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.null),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}
