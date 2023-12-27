package features.movieDetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import features.movieDetail.data.models.MovieDetailModel
import features.movieDetail.presentation.state.MovieDetailEvent
import features.movieDetail.presentation.state.MovieDetailScreenModel
import features.movieDetail.presentation.state.MovieDetailState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data class MovieDetailScreen(val id: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<MovieDetailScreenModel>()

        LaunchedEffect(id) {
            screenModel.onEvent(MovieDetailEvent.GetMovieDetail(id))
        }

        val state by screenModel.state.collectAsState()
        MovieDetail(state)
    }
}

@Composable
fun MovieDetail(state: MovieDetailState) {
    when (state) {
        is MovieDetailState.Loading -> {
            CircularProgressIndicator()
        }

        is MovieDetailState.Success -> {
            MovieDetail(state.movie)
        }
    }
}

@Composable
fun MovieDetail(model: MovieDetailModel) {
    val navigator = LocalNavigator.currentOrThrow
    val listState = rememberScrollState()
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(model.title)
            },
            navigationIcon = {
                IconButton(onClick = {
                    navigator.pop()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back to movie list"
                    )
                }
            }
        )
    }) { 
        Column(modifier = Modifier.padding(16.dp).verticalScroll(state = listState)) {
            val painerResource = asyncPainterResource("https://image.tmdb.org/t/p/original" + model.posterPath)
            KamelImage(
                resource = painerResource,
                contentDescription = "Movie image",
                onLoading = { _ ->

                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(model.originalTitle, style = MaterialTheme.typography.h5)
            Text(model.overview, style = MaterialTheme.typography.body1)
        }
    }
}
