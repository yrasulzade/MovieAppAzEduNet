package com.example.movieappazedunet.data.database

import androidx.room.*

@Dao
interface VoteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: VoteMovieData)

    @Query("SELECT * FROM vote_movie_table WHERE movie_id LIKE :movie_id")
    fun getSpecificMovie(movie_id: Long): VoteMovieData

    @Update(entity = VoteMovieData::class)
    suspend fun update(obj: VoteMovieData)
}