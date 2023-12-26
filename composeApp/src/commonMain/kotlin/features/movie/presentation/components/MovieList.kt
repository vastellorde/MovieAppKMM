package features.movie.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import features.movie.presentation.state.MovieScreenModel
import androidx.compose.runtime.LaunchedEffect
import features.movie.presentation.state.MovieEvent


@Composable
fun MovieList() {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = navigator.getNavigatorScreenModel<MovieScreenModel>()
    LaunchedEffect(Unit) {
        screenModel.onEvent(MovieEvent.GetMovieList)
    }
    val moviePagingItems = screenModel.state.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(moviePagingItems.itemCount) { index ->
            moviePagingItems[index]?.let {
                MovieCard(it)
            }
        }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadStateLoading -> {
                    items(3) {
                        LoadingMovieCard()
                    }
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