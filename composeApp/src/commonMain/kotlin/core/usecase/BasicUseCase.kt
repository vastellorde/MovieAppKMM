package core.usecase

abstract class BasicUseCase<VALUE, PARAMS> {
    abstract suspend fun invoke(params: PARAMS): VALUE 
}
