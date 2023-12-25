package features.movie.domain.di

import features.movie.data.datasource.remote.MovieApi
import features.movie.data.datasource.remote.MovieApiImpl
import features.movie.data.repository.MovieRepositoryImpl
import features.movie.domain.repository.MovieRepository
import features.movie.domain.usecase.GetMovieListUseCase
import org.koin.dsl.module

fun movieModule() = module {
    single { GetMovieListUseCase(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<MovieApi> { MovieApiImpl(get()) }
}


