package com.bayu.movieflow.core.data.source.local.room.genre

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Insert
    suspend fun insert(entities: List<GenreEntity>)

    @Query("SELECT * FROM table_genre")
    fun getAllGenre(): Flow<List<GenreEntity>>

}