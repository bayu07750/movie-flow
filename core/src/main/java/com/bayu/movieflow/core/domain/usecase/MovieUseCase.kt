package com.bayu.movieflow.core.domain.usecase

import com.bayu.movieflow.core.data.repository.vo.Resource
import com.bayu.movieflow.core.domain.model.Genre
import com.bayu.movieflow.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun searchMovies(query: String): Flow<Resource<List<Movie>>>

    fun genres(): Flow<List<Genre>>

}