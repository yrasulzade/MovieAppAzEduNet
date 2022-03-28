package com.example.movieappazedunet.presentation.ui.fragments.movie_details

import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieappazedunet.R
import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.databinding.FragmentMovieDetailsBinding
import com.example.movieappazedunet.domain.model.details.MovieDetails
import com.example.movieappazedunet.presentation.base.mvi_base.BaseMviFragment
import com.example.movieappazedunet.presentation.util.Utils.generateImageDownloadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseMviFragment<MovieDetailsIntent, MovieDetailsState, FragmentMovieDetailsBinding, MovieDetailsViewModel>() {
    private var movieId = -1L
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var voteMovieData: VoteMovieData

    override fun getViewModel(): MovieDetailsViewModel {
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        return viewModel
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_movie_details
    }

    override fun initUI() {
        viewDataBinding.viewModel = viewModel
    }

    override fun initEVENT() {
        arguments?.let {
            val args = MovieDetailsFragmentArgs.fromBundle(it)
            movieId = args.movieId
            dispatchIntent(MovieDetailsIntent.FetchMovieDetails(movieId))
        }

        viewDataBinding.ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, _, _ ->
                voteMovieData.vote = ratingBar.rating

                dispatchIntent(MovieDetailsIntent.UpdateMovieVote(voteMovieData))
            }
    }


    override fun render(state: MovieDetailsState) {
        when (state) {
            is MovieDetailsState.MovieDetailsResult -> setMovieDetails(state.movieDetails)
            is MovieDetailsState.MovieCurrentVote -> setRating(state.movieData)
            is MovieDetailsState.BackButtonClick -> findNavController().popBackStack()
        }
    }

    private fun setRating(movieData: VoteMovieData) {
        voteMovieData = movieData
        movieData.vote?.let {
            viewDataBinding.ratingBar.rating = it
        }
    }

    private fun setMovieDetails(movieDetails: MovieDetails) {
        viewDataBinding.movie = movieDetails
        viewDataBinding.genres = movieDetails.genres[0]

        movieDetails.poster_path?.let {
            Glide.with(requireContext())
                .load(movieDetails.poster_path.generateImageDownloadUrl())
                .placeholder(R.drawable.placeholder)
                .into(viewDataBinding.posterImage)
        }
    }
}