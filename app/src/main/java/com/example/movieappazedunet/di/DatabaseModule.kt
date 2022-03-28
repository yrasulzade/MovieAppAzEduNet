package com.example.movieappazedunet.di

import android.content.Context
import androidx.room.Room
import com.example.movieappazedunet.data.database.DailyDatabase
import com.example.movieappazedunet.data.database.VoteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesVoteMovieDao(dailyDatabase: DailyDatabase): VoteMovieDao = dailyDatabase.dailyDao()

    @Provides
    @Singleton
    fun providesVoteDatabase(@ApplicationContext context: Context): DailyDatabase =
        Room.databaseBuilder(context, DailyDatabase::class.java, "DailyDatabase")
            .fallbackToDestructiveMigration()
            .build()
}