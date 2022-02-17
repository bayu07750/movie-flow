package com.bayu.movieflow.core.data.source.local.room.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_genre")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
)
