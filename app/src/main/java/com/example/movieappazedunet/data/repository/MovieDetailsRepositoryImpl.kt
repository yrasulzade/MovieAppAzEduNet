package com.example.movieappazedunet.data.repository

import com.example.movieappazedunet.domain.model.details.MovieDetails
import com.example.movieappazedunet.data.api.ApiService
import com.example.movieappazedunet.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }

}