package features.genre.domain.repository

import features.genre.data.model.GenreModel

abstract class GenreRepository {
    abstract suspend fun getGenreList(): List<GenreModel>
}