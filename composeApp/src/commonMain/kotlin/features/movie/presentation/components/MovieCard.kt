package features.movie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.extensions.shimmerLoadingAnimation
import features.movie.data.model.MovieModel
import features.movieDetail.presentation.MovieDetailScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(movie: MovieModel) {
    val navigator = LocalNavigator.currentOrThrow
    Card(
        onClick = {
            navigator.push(MovieDetailScreen(movie.id.toString()))
        }
    ) {
        Column {
            MovieCardImage("https://image.tmdb.org/t/p/original${movie.backdropPath ?: movie.posterPath}")
            Text(
                movie.originalTitle,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                movie.overview,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun LoadingMovieCard() {
    val shimmerModifier = Modifier
        .padding(horizontal = 16.dp)
        .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        .fillMaxWidth()
        .shimmerLoadingAnimation()
    val spacerModifier = Modifier.height(4.dp);
    Card {
        Column {
            LoadingCardImage()
            Spacer(modifier = spacerModifier)
            Box(
                modifier = shimmerModifier.height(30.dp)
            )
            Spacer(modifier = spacerModifier)
            Box(
                modifier = shimmerModifier.height(90.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}