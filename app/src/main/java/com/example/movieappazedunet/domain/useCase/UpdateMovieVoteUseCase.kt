package com.example.movieappazedunet.domain.useCase

import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.domain.repository.VoteMovieRepository
import javax.inject.Inject

class UpdateMovieVoteUseCase @Inject constructor(private val voteMovieRepository: VoteMovieRepository) {
    suspend fun execute(voteMovieData: VoteMovieData) = voteMovieRepository.update(voteMovieData)
}