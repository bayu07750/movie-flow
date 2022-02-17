package com.bayu.movieflow.core.datamapper

import com.bayu.movieflow.core.data.source.local.room.genre.GenreEntity
import com.bayu.movieflow.core.data.source.remote.api.response.GenreResponse
import com.bayu.movieflow.core.domain.model.Genre

object GenreMapper {

    fun GenreResponse.mapResponseToDomain(): Genre {
        return Genre(
            id = id ?: 0,
            name = name.orEmpty()
        )
    }

    fun List<GenreResponse>.mapResponseToDomain(): List<Genre> {
        return map {
            it.mapResponseToDomain()
        }
    }

    fun List<GenreEntity>.mapEntityToDomain(): List<Genre> {
        return map {
            it.mapEntityToDomain()
        }
    }

    fun GenreEntity.mapEntityToDomain(): Genre {
        return Genre(id, name)
    }

    fun GenreResponse.mapResponseToEntity(): GenreEntity {
        return GenreEntity(id ?: 0, name.orEmpty())
    }

    fun List<GenreResponse>.mapResponseToEntity(): List<GenreEntity> {
        return map {
            it.mapResponseToEntity()
        }
    }

}