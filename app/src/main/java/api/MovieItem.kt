package api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieItem(
    @Json(name = "Title") val title: String,
    @Json(name = "imdbID") val id: String,
    @Json(name = "Poster") val url: String,
)

