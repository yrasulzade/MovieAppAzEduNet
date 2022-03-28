package com.example.movieappazedunet.domain.useCase

import com.example.movieappazedunet.domain.repository.MovieDetailsRepository
import com.example.movieappazedunet.domain.repository.PopularMovieRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend fun execute(movieId: Long) = repository.getMovieDetails(movieId)
}