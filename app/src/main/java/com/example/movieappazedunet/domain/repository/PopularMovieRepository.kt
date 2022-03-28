package com.example.movieappazedunet.domain.repository

import com.example.movieappazedunet.domain.model.movie.PopularMovies

interface PopularMovieRepository {
    suspend fun getPopularMovies(api_key: String, lang: String, page: Int): PopularMovies
    suspend fun searchMovies(api_key: String, query: String, page: Int): PopularMovies
}