package features.movie.domain.repository

import app.cash.paging.PagingData
import features.movie.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

abstract class MovieRepository {
    abstract suspend fun getMovieList(params: Int?): Flow<PagingData<MovieModel>>
}