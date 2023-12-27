package features.movieDetail.presentation.state

sealed class MovieDetailEvent {
    data class GetMovieDetail(val id: String) : MovieDetailEvent()
}
