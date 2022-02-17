package com.bayu.movieflow.core.data.repository

import com.bayu.movieflow.core.data.repository.vo.Resource
import com.bayu.movieflow.core.data.source.local.LocalDataSource
import com.bayu.movieflow.core.data.source.remote.RemoteDataSource
import com.bayu.movieflow.core.data.source.remote.api.ApiResponse
import com.bayu.movieflow.core.datamapper.GenreMapper.mapEntityToDomain
import com.bayu.movieflow.core.datamapper.GenreMapper.mapResponseToEntity
import com.bayu.movieflow.core.datamapper.MovieMapper.mapResponseToDomain
import com.bayu.movieflow.core.di.DefaultDispatcher
import com.bayu.movieflow.core.domain.model.Genre
import com.bayu.movieflow.core.domain.model.Movie
import com.bayu.movieflow.core.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImp @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : MovieRepository {

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remote.searchMovies(query).first()) {
            is ApiResponse.Empty -> emit(Resource.Empty())
            is ApiResponse.Error -> emit(Resource.Error(response.message))
            is ApiResponse.Success -> emit(Resource.Success(response.data.mapResponseToDomain()))
        }
    }.flowOn(defaultDispatcher)

    override fun genres(): Flow<List<Genre>> = flow {
        if (local.getAllGenre().first().isEmpty()) {
            requestGenres()
        }
        emitAll(local.getAllGenre().map { it.mapEntityToDomain() })
    }.flowOn(defaultDispatcher)

    private suspend fun requestGenres() = coroutineScope {
        when (val response = remote.genres().first()) {
            is ApiResponse.Success -> {
                val entities = response.data.mapResponseToEntity()
                local.insert(entities)
            }
            else -> { /* TODO do something when error */
            }
        }
    }

}