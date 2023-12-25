package features.movie.presentation

import features.movie.data.model.MovieModel

sealed interface MovieState {
    data object Loading: MovieState
    
    data class Success(val movieList: List<MovieModel>): MovieState
}
