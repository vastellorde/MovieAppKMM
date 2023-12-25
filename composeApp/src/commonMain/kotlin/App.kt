import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadState
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import features.movie.data.model.MovieModel
import features.movie.domain.di.movieModule
import features.movie.presentation.MovieEvent
import features.movie.presentation.MovieViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule(), movieModule())
    }) {
        val movieViewModel = MovieViewModel(koinInject())
        LaunchedEffect(Unit) {
            movieViewModel.onEvent(MovieEvent.GetMovieList)
        }
        MaterialTheme {
            
            Scaffold(topBar = {
                TopAppBar(title = {Text("MovieApp")})
            }) {
                MovieList(movieViewModel)
            }
        }
    }
}

@Composable
fun MovieList(viewModel: MovieViewModel) {
    val moviePagingItems = viewModel.state.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp),
               verticalArrangement = Arrangement.spacedBy(8.dp),
               horizontalAlignment = Alignment.CenterHorizontally,) {
       items(moviePagingItems.itemCount) { index ->
           MovieCard(moviePagingItems[index]!!)
       }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadStateLoading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.refresh is LoadStateError -> {
                    val error = moviePagingItems.loadState.refresh as LoadStateError
                    item {
                        Text(error.error.message ?: "Some error")
                    }
                }
                loadState.append is LoadStateLoading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.append is LoadStateError -> {
                    val error = moviePagingItems.loadState.append as LoadStateError
                    item {
                        Text(error.error.message ?: "Some error")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: MovieModel) {
    Card {
        Column {
            MovieCardImage("https://image.tmdb.org/t/p/original${movie.backdropPath ?: movie.posterPath}")
            Text(movie.originalTitle, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 16.dp))
            Text(movie.overview, style = MaterialTheme.typography.body1, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MovieCardImage(imageUrl: String) {
    val painerResource = asyncPainterResource(imageUrl)
    KamelImage(
        resource = painerResource,
        contentDescription = "Movie image",
        onLoading = { progress -> CircularProgressIndicator(progress) },
    )
}