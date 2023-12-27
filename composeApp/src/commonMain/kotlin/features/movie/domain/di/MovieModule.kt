package features.movie.domain.di

import features.movie.data.datasource.remote.MovieApi
import features.movie.data.datasource.remote.MovieApiImpl
import features.movie.data.repository.MovieRepositoryImpl
import features.movie.domain.repository.MovieRepository
import features.movie.domain.usecase.GetMovieListUseCase
import features.movie.presentation.state.MovieScreenModel
import org.koin.dsl.module

fun movieModule() = module {
    factory { MovieScreenModel(get()) }
    single<GetMovieListUseCase> { GetMovieListUseCase(get<MovieRepository>()::getMovieList) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<MovieApi> { MovieApiImpl(get()) }
}


