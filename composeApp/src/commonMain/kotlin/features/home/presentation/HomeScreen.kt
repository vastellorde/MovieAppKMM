package features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import features.genre.presentation.GenreList
import features.movie.presentation.components.MovieList

object HomeScreen : Screen {
    @Composable
    override fun Content() {
       HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Movie list") })
    }) {
        Column {
            GenreList()
            MovieList()
        }
    }
}
