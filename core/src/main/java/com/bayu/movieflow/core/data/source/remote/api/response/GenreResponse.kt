package com.bayu.movieflow.core.data.source.remote.api.response

import com.squareup.moshi.Json

data class GenreResponse(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null
)