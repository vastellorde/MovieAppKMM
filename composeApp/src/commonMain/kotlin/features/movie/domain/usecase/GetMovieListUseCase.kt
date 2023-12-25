package features.movie.domain.usecase

import app.cash.paging.PagingData
import core.usecase.BasicUseCase
import features.movie.data.model.MovieModel
import features.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(private val movieRepository: MovieRepository) :
    BasicUseCase<Flow<PagingData<MovieModel>>, Unit>() {
    override suspend fun invoke(params: Unit) = movieRepository.getMovieList()
}