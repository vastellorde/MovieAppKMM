package features.movieDetail.domain.usecase

import core.usecase.BasicUseCase
import features.movieDetail.data.models.MovieDetailModel
import kotlinx.coroutines.flow.Flow

fun interface GetMovieDetailUseCase : BasicUseCase<String, Flow<MovieDetailModel>>
