package features.genre.data.repository

import features.genre.data.datasource.remote.GenreApi
import features.genre.data.model.GenreModel
import features.genre.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl(private val api: GenreApi) : GenreRepository() {
    override suspend fun getGenreList(params: Unit): Flow<List<GenreModel>> = flow {
        val response = api.getGenreList().genres
        emit(response)
    }
}
