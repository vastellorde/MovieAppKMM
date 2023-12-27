package features.movieDetail.domain.di

import features.movieDetail.data.datasource.remote.MovieDetailApi
import features.movieDetail.data.datasource.remote.MovieDetailApiImpl
import features.movieDetail.data.repository.MovieDetailRepositoryImpl
import features.movieDetail.domain.repository.MovieDetailRepository
import features.movieDetail.domain.usecase.GetMovieDetailUseCase
import features.movieDetail.presentation.state.MovieDetailScreenModel
import org.koin.dsl.module

fun movieDetailModule() = module {
    factory { MovieDetailScreenModel(get()) }
    single { GetMovieDetailUseCase(get<MovieDetailRepository>()::getMovieDetail) }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
    single<MovieDetailApi> { MovieDetailApiImpl(get()) }
}
