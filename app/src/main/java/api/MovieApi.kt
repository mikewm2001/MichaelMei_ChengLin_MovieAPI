package api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "bf0abf8"

interface MovieApi {
    @GET("?apikey=$API_KEY")
    suspend fun fetchMovie(@Query("t") query: String): MovieItem

}
