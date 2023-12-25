import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import features.movie.domain.di.movieModule
import features.movie.presentation.MovieScreen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule(), movieModule())
    }) {
        MaterialTheme {
            Navigator(MovieScreen)
        }
    }
}