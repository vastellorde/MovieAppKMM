package core.http

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createHttpClient(baseUrl: String) =
    HttpClient {
        defaultRequest {
            url(baseUrl)
        }
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true; })
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMGQ0NzYzM2IzYjZkM2JlMjYyY2ExNTEyZDdmZGNmMSIsInN1YiI6IjY1ODZjZjgzNDc3MjE1NTlkMzQzNjYwMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SmO0_j_xhVPDWtF1aiDyKJnwM0-IGzbuP0i_R4kV5HY",
                        ""
                    )
                }
                sendWithoutRequest { request ->
                    request.url.host.contains("themoviedb")
                }
            }
        }
    }