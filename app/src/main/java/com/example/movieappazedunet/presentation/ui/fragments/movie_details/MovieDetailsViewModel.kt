package com.example.movieappazedunet.presentation.ui.fragments.movie_details

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieappazedunet.data.database.VoteMovieData
import com.example.movieappazedunet.domain.useCase.FetchMovieVoteUseCase
import com.example.movieappazedunet.domain.useCase.MovieDetailsUseCase
import com.example.movieappazedunet.domain.useCase.UpdateMovieVoteUseCase
import com.example.movieappazedunet.domain.useCase.VoteMovieUseCase
import com.example.movieappazedunet.presentation.base.mvi_base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val voteMovieUseCase: VoteMovieUseCase,
    private val fetchMovieVoteUseCase: FetchMovieVoteUseCase,
    private val updateMovieVoteUseCase: UpdateMovieVoteUseCase
) : BaseViewModel<MovieDetailsIntent, MovieDetailsState>() {

    private fun getMovieDetails(movieId: Long) {

        viewModelScope.launch {
            flow {
                emit(movieDetailsUseCase.execute(movieId))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion { loading(false) }
                .catch { errorLiveData.value = it }
                .collect {
                    mState.value = MovieDetailsState.MovieDetailsResult(it)
                }
        }
    }

    private fun updateMovieVote(movieVote: VoteMovieData) {
        Log.d("updateMovieVote", "updateMovieVote: ${movieVote?.vote}")

        viewModelScope.launch {
            flow {
                emit(updateMovieVoteUseCase.execute(movieVote))
            }.flowOn(Dispatchers.IO)
                .collect {
                    mState.value = MovieDetailsState.MovieCurrentVote(movieVote)
                }
        }
    }

    private fun voteMovie(movieId: Long) {
        val movieVote = VoteMovieData(movieId, 0f)

        viewModelScope.launch {
            flow {
                emit(voteMovieUseCase.execute(movieVote))
            }.flowOn(Dispatchers.IO)
                .collect {
                    fetchMovieVote(movieId)
                }
        }
    }

    private fun fetchMovieVote(movieId: Long) {

        viewModelScope.launch {
            flow {
                emit(fetchMovieVoteUseCase.execute(movieId))
            }.flowOn(Dispatchers.IO)
                .collect {
                    if (it == null) {
                        voteMovie(movieId)
                    } else {
                        mState.value = MovieDetailsState.MovieCurrentVote(it)
                    }
                }
        }
    }

    fun backButtonClick(){
        mState.value =  MovieDetailsState.BackButtonClick
    }

    override fun handleIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.FetchMovieDetails -> {
                getMovieDetails(intent.movieId)
                fetchMovieVote(intent.movieId)
            }
            is MovieDetailsIntent.UpdateMovieVote -> updateMovieVote(intent.movieData)
        }
    }
}