package features.movie.presentation.state

import androidx.paging.cachedIn
import app.cash.paging.PagingData
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import features.movie.data.model.MovieModel
import features.movie.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MovieScreenModel(private val getMovieListUseCase: GetMovieListUseCase) : StateScreenModel<PagingData<MovieModel>>(
    PagingData.empty()
) {

    fun onEvent(event: MovieEvent) {
        screenModelScope.launch {
            when (event) {
                is MovieEvent.GetMovieList -> {
                    getMovies()
                }
            }
        }
    }

    private suspend fun getMovies() {
        getMovieListUseCase.invoke(Unit)
            .distinctUntilChanged()
            .cachedIn(screenModelScope)
            .collect {
                mutableState.value = it
            }
    }
}