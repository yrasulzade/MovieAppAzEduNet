package com.example.movieappazedunet.domain.model

data class Error(
    val message: String? = null,
    val type: String? = null,
    val title: String? = null,
    val status: Int? = null
)