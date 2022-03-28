package com.example.movieappazedunet.domain.model.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class PopularMovies(
    @Json(name = "page")
    var page: Int? = null,

    @Json(name = "total_results")
    val total_results: Int? = null,

    @Json(name = "total_pages")
    val total_pages: Int? = null,

    @Json(name = "results")
    val results: List<Result>
)