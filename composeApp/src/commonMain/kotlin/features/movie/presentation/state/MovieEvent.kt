package features.movie.presentation.state

sealed class MovieEvent {
    data class GetMovieList(val genreId: Int? = null) : MovieEvent()
}
