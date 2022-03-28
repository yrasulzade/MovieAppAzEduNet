package com.example.movieappazedunet.presentation.ui.fragments.popular

import com.example.movieappazedunet.domain.model.movie.PopularMovies
import com.example.movieappazedunet.presentation.base.view_state.ViewState

sealed class PopularMoviesState : ViewState {
    data class PopularMoviesResult(val popularMovies: PopularMovies, val clearAllData: Boolean,val isLastPage: Boolean) : PopularMoviesState()
}
