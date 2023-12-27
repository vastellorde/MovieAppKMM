package features.movieDetail.presentation.state

import features.movieDetail.data.models.MovieDetailModel

sealed class MovieDetailState {
    data object Loading : MovieDetailState()
    data class Success(val movie: MovieDetailModel) : MovieDetailState()
}
