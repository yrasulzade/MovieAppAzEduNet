package com.example.movieappazedunet.domain.useCase

import com.example.movieappazedunet.domain.repository.PopularMovieRepository
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(private val repository: PopularMovieRepository) {
    suspend fun execute(api_key: String, lang: String, page: Int) = repository.getPopularMovies(api_key, lang, page)
}