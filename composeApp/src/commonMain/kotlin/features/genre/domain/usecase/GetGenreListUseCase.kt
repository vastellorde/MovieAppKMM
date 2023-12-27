package features.genre.domain.usecase

import core.usecase.BasicUseCase
import features.genre.data.model.GenreModel
import kotlinx.coroutines.flow.Flow

fun interface GetGenreListUseCase : BasicUseCase<Unit, Flow<List<GenreModel>>>