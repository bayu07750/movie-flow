package com.bayu.movieflow.core.domain.usecase

import com.bayu.movieflow.core.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieInteraction @Inject constructor(
    movieRepository: MovieRepository
) : MovieUseCase by movieRepository