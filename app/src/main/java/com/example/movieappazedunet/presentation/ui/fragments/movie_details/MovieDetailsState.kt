package com.example.movieappazedunet.presentation.ui.fragments.movie_details

import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.domain.model.details.MovieDetails
import com.example.movieappazedunet.presentation.base.view_state.ViewState

sealed class MovieDetailsState : ViewState {
    data class MovieDetailsResult(val movieDetails: MovieDetails) : MovieDetailsState()
    data class MovieCurrentVote(val movieData: VoteMovieData) : MovieDetailsState()
    object BackButtonClick : MovieDetailsState()
}
