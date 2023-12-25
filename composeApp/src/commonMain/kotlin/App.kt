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
import features.movie.presentation.MovieView
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
                TopAppBar(title = { Text("MovieApp") })
            }) {
                MovieView(movieViewModel)
            }
        }
    }
}