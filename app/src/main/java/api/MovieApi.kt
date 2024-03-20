package api

import retrofit2.http.GET

private const val API_KEY = "bf0abf8"
private const val movie = "dune"

interface MovieApi {
    @GET(
        "?apikey=$API_KEY" +
                "&t=$movie"
    )
    suspend fun fetchMovie(): MovieItem

}
