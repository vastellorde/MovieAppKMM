import core.http.createHttpClient
import org.koin.dsl.module

fun appModule() = module {
    single { createHttpClient("https://api.themoviedb.org/3/") }
}



