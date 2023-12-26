package features.genre.presentation.state

sealed class GenreEvent {
    data object GetGenreList: GenreEvent()
}
