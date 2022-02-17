package com.bayu.movieflow.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bayu.movieflow.core.data.source.local.room.genre.GenreDao
import com.bayu.movieflow.core.data.source.local.room.genre.GenreEntity

@Database(
    entities = [GenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao

}