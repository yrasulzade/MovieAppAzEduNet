package com.example.movieappazedunet.domain.model.details

import com.squareup.moshi.Json

data class MovieDetails(
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollection,

    @Json(name = "budget")
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,

    @Json(name = "imdb_id")
    val imdbID: String,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @Json(name = "poster_path")
    val poster_path: String? = null,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>,

    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>,

    @Json(name = "release_date")
    val release_date: String,

    val revenue: Long,
    val runtime: Long,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @Json(name = "vote_average")
    val vote_average: Double,

    @Json(name = "vote_count")
    val voteCount: Long
) {
    fun getBudgetInDollars(): String {
        return if (budget!=0L){
            "$ $budget"
        }else{
            "No info"
        }
    }
}

data class BelongsToCollection(
    val id: Long,
    val name: String,

    @Json(name = "poster_path")
    val posterPath: Any? = null,

    @Json(name = "backdrop_path")
    val backdropPath: Any? = null
)

data class Genre(
    val id: Long,
    val name: String
)

data class ProductionCompany(
    val id: Long,

    @Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String,

    @Json(name = "origin_country")
    val originCountry: String
)

data class ProductionCountry(
    @Json(name = "iso_3166_1")
    val iso3166_1: String,

    val name: String
)

data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String,

    @Json(name = "iso_639_1")
    val iso639_1: String,

    val name: String
)