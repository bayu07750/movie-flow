package com.bayu.movieflow.core.data.source.remote

import com.bayu.movieflow.core.di.IODispatcher
import com.bayu.movieflow.core.data.source.remote.api.ApiResponse
import com.bayu.movieflow.core.data.source.remote.api.ApiService
import com.bayu.movieflow.core.data.source.remote.api.response.GenreResponse
import com.bayu.movieflow.core.data.source.remote.api.response.MovieResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    fun searchMovies(query: String): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.searchMovies(query = query)
            val moviesResponse = response.results
            if (moviesResponse?.isNotEmpty() == true) {
                emit(ApiResponse.Success(moviesResponse))
            } else {
                emit(ApiResponse.Empty())
            }
        } catch (e: IOException) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(ioDispatcher)

    fun genres(): Flow<ApiResponse<List<GenreResponse>>> = flow {
        try {
            val response = apiService.genres()
            val genresResponse = response.genres
            if (genresResponse?.isNotEmpty() == true) {
                emit(ApiResponse.Success(genresResponse))
            } else {
                emit(ApiResponse.Empty())
            }
        } catch (e: IOException) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(ioDispatcher)

}