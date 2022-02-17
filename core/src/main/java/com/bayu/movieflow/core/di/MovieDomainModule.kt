package com.bayu.movieflow.core.di

import com.bayu.movieflow.core.data.repository.MovieRepositoryImp
import com.bayu.movieflow.core.domain.repository.MovieRepository
import com.bayu.movieflow.core.domain.usecase.MovieInteraction
import com.bayu.movieflow.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDomainModule {

    @Binds
    abstract fun provideMovieRepository(impl: MovieRepositoryImp): MovieRepository

    @Binds
    abstract fun provideMovieUseCase(impl: MovieInteraction): MovieUseCase

}