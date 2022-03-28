package com.example.movieappazedunet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VoteMovieData::class],
    version = 3,
    exportSchema = false
)
abstract class DailyDatabase : RoomDatabase() {
    abstract fun dailyDao(): VoteMovieDao
}
