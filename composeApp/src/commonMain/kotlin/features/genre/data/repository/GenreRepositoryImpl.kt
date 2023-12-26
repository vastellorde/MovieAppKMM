package features.genre.data.repository

import features.genre.data.datasource.remote.GenreApi
import features.genre.data.model.GenreModel
import features.genre.domain.repository.GenreRepository

class GenreRepositoryImpl(private val api: GenreApi) : GenreRepository() {
    override suspend fun getGenreList(): List<GenreModel> = api.getGenreList().genres
}
