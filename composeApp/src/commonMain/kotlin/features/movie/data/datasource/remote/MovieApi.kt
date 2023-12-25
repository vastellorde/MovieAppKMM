package features.movie.data.datasource.remote

import core.models.PaginatedResponse
import features.movie.data.model.MovieModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

abstract class MovieApi {
    abstract suspend fun getMovieList(page: Int): PaginatedResponse<List<MovieModel>>
}

class MovieApiImpl(private val httpClient: HttpClient): MovieApi() {
    override suspend fun getMovieList(page: Int): PaginatedResponse<List<MovieModel>> {
        val url = "discover/movie?include_adult=false&include_video=false&language=en-US&page=$page&sort_by=popularity.desc"
        val response = httpClient
            .get(url)
        return response.body()
    }
}
