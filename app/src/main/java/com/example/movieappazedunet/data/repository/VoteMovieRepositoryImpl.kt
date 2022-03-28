package com.example.movieappazedunet.data.repository

import com.example.movieappazedunet.data.database.VoteMovieDao
import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.domain.repository.VoteMovieRepository
import javax.inject.Inject

class VoteMovieRepositoryImpl @Inject constructor(private val dao: VoteMovieDao) :
    VoteMovieRepository {

    override suspend fun voteMovie(voteMovieData: VoteMovieData) {
        dao.insert(voteMovieData)
    }

    override suspend fun getMovieVote(movieId: Long): VoteMovieData {
        return dao.getSpecificMovie(movieId)
    }

    override suspend fun update(voteMovieData: VoteMovieData) {
        return dao.update(voteMovieData)
    }
}