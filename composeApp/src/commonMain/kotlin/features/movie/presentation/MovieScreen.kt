package features.movie.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import features.movie.presentation.components.MovieList

object MovieScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Movie list") })
        }) {
            MovieList()
        }
    }
}
