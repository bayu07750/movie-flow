package com.bayu.movieflow.core.data.source.local

import com.bayu.movieflow.core.data.source.local.room.genre.GenreDao
import com.bayu.movieflow.core.data.source.local.room.genre.GenreEntity
import com.bayu.movieflow.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: GenreDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun insert(entities: List<GenreEntity>) {
        withContext(Dispatchers.IO) {
            dao.insert(entities)
        }
    }

    fun getAllGenre(): Flow<List<GenreEntity>> = dao.getAllGenre().flowOn(ioDispatcher)

}