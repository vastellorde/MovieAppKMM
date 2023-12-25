package features.movie.domain.usecase

import features.movie.domain.repository.MovieRepository

class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend fun invoke() = movieRepository.getMovieList()
}