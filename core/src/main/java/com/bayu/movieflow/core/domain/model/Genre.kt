package com.bayu.movieflow.core.domain.model

data class Genre(
    val id: Int,
    val name: String,
    var isSelected: Int = 0,
)