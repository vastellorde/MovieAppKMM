package features.movie.presentation.state

sealed class MovieEvent {
    data object GetMovieList : MovieEvent()
}
