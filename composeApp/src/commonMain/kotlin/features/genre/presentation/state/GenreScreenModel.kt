package features.genre.presentation.state

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import features.genre.data.model.GenreModel
import features.genre.domain.usecase.GetGenreListUseCase
import kotlinx.coroutines.launch

class GenreScreenModel(private val getGenreListUseCase: GetGenreListUseCase) :
    StateScreenModel<List<GenreModel>>(emptyList()) {
    fun onEvent(event: GenreEvent) {
        screenModelScope.launch {
            when (event) {
                is GenreEvent.GetGenreList -> {
                    getGenres()
                }
            }
        }
    }
    
    private suspend fun getGenres() {
        getGenreListUseCase(Unit)
            .collect {
                mutableState.value = it
            }
    }
}