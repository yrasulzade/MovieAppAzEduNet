package com.example.movieappazedunet.domain.useCase

import com.example.movieappazedunet.domain.repository.VoteMovieRepository
import javax.inject.Inject

class FetchMovieVoteUseCase @Inject constructor(private val voteMovieRepository: VoteMovieRepository) {
    suspend fun execute(movieId:Long) = voteMovieRepository.getMovieVote(movieId)
}