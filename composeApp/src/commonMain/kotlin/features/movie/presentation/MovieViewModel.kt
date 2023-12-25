package features.movie.presentation

import androidx.paging.cachedIn
import app.cash.paging.PagingData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import features.movie.data.model.MovieModel
import features.movie.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MovieViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {
    private val _state = MutableStateFlow<PagingData<MovieModel>>(value = PagingData.empty())
    var state = _state.asStateFlow()
    
    fun onEvent(intent: MovieEvent) {
        viewModelScope.launch {
            when (intent) {
                is MovieEvent.GetMovieList -> {
                    getMovies()
                }
            }
        }
    }
    
    private suspend fun getMovies() {
        getMovieListUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                println(it)
                _state.value = it
            }
    }
}