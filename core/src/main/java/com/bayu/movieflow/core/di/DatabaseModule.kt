package com.bayu.movieflow.core.di

import android.content.Context
import androidx.room.Room
import com.bayu.movieflow.core.data.source.local.room.AppDatabase
import com.bayu.movieflow.core.data.source.local.room.genre.GenreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "movies_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideGenreDao(db: AppDatabase): GenreDao {
        return db.genreDao()
    }

}