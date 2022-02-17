package com.bayu.movieflow.core.domain.model

data class Movie(
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalTitle: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
) {

    val urlBackdrop get() = "https://image.tmdb.org/t/p/w500${backdropPath}"

}