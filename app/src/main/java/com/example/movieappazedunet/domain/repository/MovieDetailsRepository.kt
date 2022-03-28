package com.example.movieappazedunet.domain.repository

import com.example.movieappazedunet.domain.model.details.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long): MovieDetails
}