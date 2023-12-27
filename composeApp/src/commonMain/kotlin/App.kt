import androidx.compose.material.*
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import features.genre.domain.di.genreModule
import features.home.domain.di.homeModule
import features.home.presentation.HomeScreen
import features.movie.domain.di.movieModule
import features.movieDetail.domain.di.movieDetailModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule(), homeModule(), movieModule(), genreModule(), movieDetailModule())
    }) {
        MaterialTheme {
            Navigator(HomeScreen)
        }
    }
}