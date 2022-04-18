package com.bayu.movieflow.core.data.source.remote.api.response

import com.squareup.moshi.Json

data class MoviesResponse(
    @field:Json(name = "page")
    val page: Int? = null,
    @field:Json(name = "results")
    val results: List<MovieResponse>? = null,
    @field:Json(name = "total_pages")
    val totalPages: Int? = null,
    @field:Json(name = "total_results")
    val totalResults: Int? = null
)