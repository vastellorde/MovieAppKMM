package features.movie.presentation.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieCardImage(imageUrl: String) {
    val painerResource = asyncPainterResource(imageUrl)
    KamelImage(
        resource = painerResource,
        contentDescription = "Movie image",
        onLoading = { progress -> CircularProgressIndicator(progress) },
        )
}