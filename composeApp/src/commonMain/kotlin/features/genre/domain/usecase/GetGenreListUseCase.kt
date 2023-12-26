package features.genre.domain.usecase

import core.usecase.BasicUseCase
import features.genre.data.model.GenreModel
import features.genre.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenreListUseCase(private val repository: GenreRepository) : BasicUseCase<Flow<List<GenreModel>>, Unit>() {
    override suspend fun invoke(params: Unit) = flow {
        val res = repository.getGenreList()
        emit(res)
    }
}