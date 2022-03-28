package com.example.movieappazedunet.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vote_movie_table")
data class VoteMovieData(
    var movie_id: Long? = null,
    var vote: Float? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


}