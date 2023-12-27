package features.movie.domain.usecase

import app.cash.paging.PagingData
import core.usecase.BasicUseCase
import features.movie.data.model.MovieModel
import kotlinx.coroutines.flow.Flow


fun interface GetMovieListUseCase : BasicUseCase<Int?, Flow<PagingData<MovieModel>>>