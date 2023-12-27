package features.home.presentation.state

import cafe.adriel.voyager.core.model.StateScreenModel

class HomeScreenModel() : StateScreenModel<HomeState>(HomeState()) {
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectGenre -> {
                addGenre(event.genre)
            }
        }
    }

    private fun addGenre(genre: Int) {
        mutableState.value = mutableState.value.copy(selectedGenres = genre)
    }
}
