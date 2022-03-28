package com.example.movieappazedunet.data.api

import com.example.movieappazedunet.domain.model.details.MovieDetails
import com.example.movieappazedunet.domain.model.movie.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String?,
        @Query("language") lang: String?,
        @Query("page") number: Int
    ): PopularMovies

    @GET("search/movie")
    suspend fun getSearch(
        @Query("api_key") api_key: String?,
        @Query("query") query: String?,
        @Query("page") number: Int
    ): PopularMovies

    @GET("movie/{movieId}?api_key=6455b4a645478f665c3e0e0d5cabf661")
    suspend fun getMovieDetails(@Path("movieId") movieId: Long): MovieDetails
}
