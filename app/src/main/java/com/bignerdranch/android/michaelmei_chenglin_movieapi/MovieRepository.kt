package com.bignerdranch.android.michaelmei_chenglin_movieapi

import api.MovieApi
import api.MovieItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MovieRepository {
    private val movieApi: MovieApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        movieApi = retrofit.create()
    }

    suspend fun fetchMovie(title: String): MovieItem =
        movieApi.fetchMovie(title)


}