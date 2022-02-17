package com.bayu.movieflow.core.data.source.remote.api

import com.bayu.movieflow.core.BuildConfig
import com.bayu.movieflow.core.data.source.remote.api.response.GenresResponse
import com.bayu.movieflow.core.data.source.remote.api.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("query") query: String
    ) : MoviesResponse

    @GET("genre/movie/list")
    suspend fun genres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US",
    ) : GenresResponse

}