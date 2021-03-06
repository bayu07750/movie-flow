package com.bayu.movieflow.core.data.source.remote.api.response


import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>? = null
)