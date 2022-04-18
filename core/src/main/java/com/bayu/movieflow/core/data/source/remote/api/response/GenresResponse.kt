package com.bayu.movieflow.core.data.source.remote.api.response

import com.squareup.moshi.Json

data class GenresResponse(
    @field:Json(name = "genres")
    val genres: List<GenreResponse>? = null
)