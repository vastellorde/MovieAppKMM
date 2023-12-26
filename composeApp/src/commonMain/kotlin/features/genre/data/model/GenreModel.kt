package features.genre.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    val id: Int,
    val name: String,
)

@Serializable
data class GenreResponse(
    val genres: List<GenreModel>
)
