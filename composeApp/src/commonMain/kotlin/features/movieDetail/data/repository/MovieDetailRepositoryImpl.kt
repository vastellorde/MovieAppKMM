package features.movieDetail.data.repository

import features.movieDetail.data.datasource.remote.MovieDetailApi
import features.movieDetail.data.models.MovieDetailModel
import features.movieDetail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(private val api: MovieDetailApi) : MovieDetailRepository() {
    override suspend fun getMovieDetail(id: String): Flow<MovieDetailModel> = flow {
        val res = api.getMovieDetail(id)
        emit(res)
    }
}
