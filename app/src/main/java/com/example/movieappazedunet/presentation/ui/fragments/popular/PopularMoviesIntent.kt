package com.example.movieappazedunet.presentation.ui.fragments.popular

import com.example.movieappazedunet.presentation.base.view_state.ViewIntent

sealed class PopularMoviesIntent : ViewIntent {
    data class FetchPopularMovies(val page: Int, val clearAllData: Boolean) : PopularMoviesIntent()
    data class SearchMovies(val query: String, val page: Int, val clearAllData: Boolean) : PopularMoviesIntent()
}
