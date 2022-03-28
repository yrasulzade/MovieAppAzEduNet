package com.example.movieappazedunet.presentation.ui.fragments.movie_details

import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.presentation.base.view_state.ViewIntent

sealed class MovieDetailsIntent : ViewIntent {
    data class FetchMovieDetails(val movieId: Long) : MovieDetailsIntent()
    data class UpdateMovieVote(val movieData: VoteMovieData) : MovieDetailsIntent()
}
