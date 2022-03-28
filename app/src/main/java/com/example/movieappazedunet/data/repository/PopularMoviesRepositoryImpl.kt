package com.example.movieappazedunet.data.repository

import com.example.movieappazedunet.data.api.ApiService
import com.example.movieappazedunet.domain.model.movie.PopularMovies
import com.example.movieappazedunet.domain.repository.PopularMovieRepository
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    PopularMovieRepository {

    override suspend fun getPopularMovies(api_key: String, lang: String, page: Int): PopularMovies {
        return apiService.getPopularMovies(api_key, lang, page)
    }

    override suspend fun searchMovies(api_key: String, query: String, page: Int): PopularMovies {
        return apiService.getSearch(api_key, query, page)
    }
}