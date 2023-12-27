package features.movie.data.datasource.remote

import core.models.PaginatedResponse
import features.movie.data.model.MovieModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

abstract class MovieApi {
    abstract suspend fun getMovieList(page: Int, genreId: Int?): PaginatedResponse<List<MovieModel>>
}

class MovieApiImpl(private val httpClient: HttpClient) : MovieApi() {
    override suspend fun getMovieList(page: Int, genreId: Int?): PaginatedResponse<List<MovieModel>> {
        val response = httpClient
            .get("discover/movie") {
                url {
                    parameters.append("include_adult", "false")
                    parameters.append("include_video", "false")
                    parameters.append("language", "en-US")
                    parameters.append("page", page.toString())
                    parameters.append("sort_by", "popularity.desc")
                    if (genreId != null) {
                        parameters.append("with_genres", genreId.toString())
                    }
                }
            }
        return response.body()
    }
}
