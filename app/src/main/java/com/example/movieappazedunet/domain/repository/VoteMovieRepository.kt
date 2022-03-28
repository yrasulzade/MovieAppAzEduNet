package com.example.movieappazedunet.domain.repository

import com.example.movieappazedunet.data.database.VoteMovieData

interface VoteMovieRepository {
    suspend fun voteMovie(voteMovieData: VoteMovieData)
    suspend fun getMovieVote(movieId: Long): VoteMovieData?
    suspend fun update(voteMovieData: VoteMovieData)
}