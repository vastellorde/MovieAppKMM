package features.movieDetail.data.datasource.remote

import features.movieDetail.data.models.MovieDetailModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

abstract class MovieDetailApi {
    abstract suspend fun getMovieDetail(id: String) : MovieDetailModel
}

class MovieDetailApiImpl(private val httpClient: HttpClient) : MovieDetailApi() {
    override suspend fun getMovieDetail(id: String): MovieDetailModel {
        val response = httpClient.get("movie/$id") {
            url {
                parameters.append("language", "en-US")
            }
        }
        return response.body()
    }

}
