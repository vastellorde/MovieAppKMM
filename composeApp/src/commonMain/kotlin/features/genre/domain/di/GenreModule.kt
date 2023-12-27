package features.genre.domain.di

import features.genre.data.datasource.remote.GenreApi
import features.genre.data.datasource.remote.GenreApiImpl
import features.genre.data.repository.GenreRepositoryImpl
import features.genre.domain.repository.GenreRepository
import features.genre.domain.usecase.GetGenreListUseCase
import features.genre.presentation.state.GenreScreenModel
import org.koin.dsl.module

fun genreModule() = module {
    factory { GenreScreenModel(get()) }
    single<GetGenreListUseCase> { GetGenreListUseCase(get<GenreRepository>()::getGenreList) }
    single<GenreRepository> { GenreRepositoryImpl(get()) }
    single<GenreApi> { GenreApiImpl(get()) }
}
