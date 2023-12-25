package features.movie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.extensions.shimmerLoadingAnimation
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieCardImage(imageUrl: String) {
    val painerResource = asyncPainterResource(imageUrl)

    KamelImage(
        resource = painerResource,
        contentDescription = "Movie image",
        onLoading = { _ ->
            LoadingCardImage()
        },
    )
}

@Composable
fun LoadingCardImage() {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth()
            .height(180.dp)
            .shimmerLoadingAnimation()
    )
}