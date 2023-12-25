package features.movie.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import features.movie.presentation.components.MovieCard


@Composable
fun MovieView(movieViewModel: MovieViewModel) {
    val moviePagingItems = movieViewModel.state.collectAsLazyPagingItems()
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