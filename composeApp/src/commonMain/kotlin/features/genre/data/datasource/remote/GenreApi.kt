package features.genre.data.datasource.remote

import features.genre.data.model.GenreResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

abstract class GenreApi {
    abstract suspend fun getGenreList(): GenreResponse
}

class GenreApiImpl(private val httpClient: HttpClient) : GenreApi() {
    override suspend fun getGenreList(): GenreResponse {
        return httpClient
            .get("genre/movie/list") {
                url {
                    parameters.append("language", "en")
                }
            }
            .body()
    }
}