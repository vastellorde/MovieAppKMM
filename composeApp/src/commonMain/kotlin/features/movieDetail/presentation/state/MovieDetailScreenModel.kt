package features.movieDetail.presentation.state

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import features.movieDetail.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch

class MovieDetailScreenModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    StateScreenModel<MovieDetailState>(MovieDetailState.Loading) {
    fun onEvent(event: MovieDetailEvent) {
        screenModelScope.launch {
            when (event) {
                is MovieDetailEvent.GetMovieDetail -> {
                    getMovieDetail(event.id)
                }
            }
        }
    }

    private suspend fun getMovieDetail(id: String) {
        getMovieDetailUseCase(id)
            .collect {
                mutableState.value = MovieDetailState.Success(it)
            }
    }
}