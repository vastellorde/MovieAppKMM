package features.movie.presentation

sealed class MovieEvent {
    data object GetMovieList : MovieEvent()
}
