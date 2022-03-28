package com.example.movieappazedunet.domain.useCase

import com.example.movieappazedunet.domain.repository.PopularMovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repository: PopularMovieRepository) {
    suspend fun execute(api_key: String, query: String, page: Int) = repository.searchMovies(api_key, query, page)
}