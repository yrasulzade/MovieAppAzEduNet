package com.example.movieappazedunet.domain.model.movie

import com.squareup.moshi.Json

data class Result(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIDS: List<Long>,
    val id: Long,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val poster_path: String? = null,
    @Json(name = "release_date")
    val release_date: String? = null,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Long
)