package features.movieDetail.domain.repository

import features.movieDetail.data.models.MovieDetailModel
import kotlinx.coroutines.flow.Flow

abstract class MovieDetailRepository {
    abstract suspend fun getMovieDetail(id: String) : Flow<MovieDetailModel>
}
