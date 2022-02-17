package com.bayu.movieflow.core.datamapper

import com.bayu.movieflow.core.data.source.remote.api.response.MovieResponse
import com.bayu.movieflow.core.domain.model.Movie

object MovieMapper {
    fun MovieResponse.mapResponseToDomain(): Movie {
        return Movie(
            backdropPath = backdropPath.orEmpty(),
            genreIds = genreIds.orEmpty(),
            id = id ?: 0,
            originalTitle = originalTitle.orEmpty(),
            posterPath = posterPath.orEmpty(),
            title = title.orEmpty(),
            voteAverage = voteAverage ?: 0.0
        )
    }

    fun List<MovieResponse>.mapResponseToDomain(): List<Movie> {
        return map {
            it.mapResponseToDomain()
        }
    }
}