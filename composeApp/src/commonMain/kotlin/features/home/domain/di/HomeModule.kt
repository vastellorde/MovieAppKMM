package features.home.domain.di

import features.home.presentation.state.HomeScreenModel
import org.koin.dsl.module

fun homeModule() = module {
    factory { HomeScreenModel() }
}
