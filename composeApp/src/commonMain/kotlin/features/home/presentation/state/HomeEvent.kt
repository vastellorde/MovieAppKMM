package features.home.presentation.state

sealed class HomeEvent {
    data class SelectGenre(val genre: Int) : HomeEvent()
}