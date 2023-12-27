package features.genre.domain.repository

import features.genre.data.model.GenreModel
import kotlinx.coroutines.flow.Flow

abstract class GenreRepository {
    abstract suspend fun getGenreList(params: Unit): Flow<List<GenreModel>>
}