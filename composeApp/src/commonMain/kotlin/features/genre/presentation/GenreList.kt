package features.genre.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import features.home.presentation.state.HomeEvent
import features.home.presentation.state.HomeScreenModel


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

@Composable
fun GenreList(genreList: List<GenreModel>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(horizontal = 16.dp)) {
        if (genreList.isEmpty()) {
            items(8) {
                Box(
                    Modifier
                        .background(
                            color = Color.LightGray,
                            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
                        )
                        .size(width = 60.dp, height = 32.dp)
                        .shimmerLoadingAnimation()
                )
            }
        } else {
            items(items = genreList, key = { genre -> genre.id }) {
                GenreItem(it)
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreItem(model: GenreModel) {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = navigator.getNavigatorScreenModel<HomeScreenModel>()
    val state by screenModel.state.collectAsState()
    val selected by remember {
        derivedStateOf {
            state.selectedGenres == model.id
        }
    }
    FilterChip(
        onClick = {
            screenModel.onEvent(HomeEvent.SelectGenre(model.id))
        },
        selected = selected,
        leadingIcon = if (selected) {
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
        Text(model.name)
    }
}