package com.example.moviebuffsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviebuffsapp.model.MovieBuffsApp
import com.example.moviebuffsapp.ui.theme.MovieBuffsAppTheme
import javax.sql.DataSource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBuffsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieBuffsApp()
                }
            }
        }
    }
}

@Composable
fun MovieBuffsApp() {
    MovieBuffsAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            @Composable
            fun MovieBuffsAppPhotos(movieBuffs: List<MovieBuffsApp>, onItemClick: (MovieBuffsApp) -> Unit) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(movieBuffs) { movieBuff ->
                        MovieBuffsAppPhotoItem(movieBuff = movieBuff, onItemClick = onItemClick)
                    }
                }
            }

            @Composable
            fun MovieBuffsAppPhotoItem(movieBuff: MovieBuffsApp, onItemClick: (MovieBuffsApp) -> Unit) {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { onItemClick(movieBuff) },
                    elevation = 4.dp
                ) {
                    Image(
                        painter = painterResource(id = movieBuff.imageResourceId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    }
}

