package core.usecase

fun interface BasicUseCase<INPUT, OUTPUT> : suspend (INPUT) -> OUTPUT