package features.genre.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.extensions.shimmerLoadingAnimation
import features.genre.data.model.GenreModel
import features.genre.presentation.state.GenreEvent
import features.genre.presentation.state.GenreScreenModel
import features.movie.presentation.state.MovieEvent
import features.movie.presentation.state.MovieScreenModel
import kotlinx.coroutines.launch


@Composable
fun GenreList() {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = navigator.getNavigatorScreenModel<GenreScreenModel>()

    LaunchedEffect(Unit) {
        screenModel.onEvent(GenreEvent.GetGenreList)
    }

    val state by screenModel.state.collectAsState()
    GenreList(state)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreList(genreList: List<GenreModel>) {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = navigator.getNavigatorScreenModel<MovieScreenModel>()
    var selectedId: Int? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope()
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        if (genreList.isEmpty()) {
            items(8) {
                Box(
                    Modifier
                        .background(color = Color.LightGray)
                        .size(width = 60.dp, height = 32.dp)
                        .border(
                            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
                            width = 0.dp,
                            color = Color.LightGray
                        )
                        .shimmerLoadingAnimation()
                )
            }
        } else {
            items(items = genreList, key = { genre -> genre.id }) {
                FilterChip(
                    onClick = {
                        selectedId = it.id
                        scope.launch {
                            screenModel.onEvent(MovieEvent.GetMovieList)
                        }
                    },
                    selected = selectedId == it.id,
                    leadingIcon = if (selectedId == it.id) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                            )
                        }
                    } else {
                        null
                    },
                ) {
                    Text(it.name)
                }
            }
        }

    }
}
