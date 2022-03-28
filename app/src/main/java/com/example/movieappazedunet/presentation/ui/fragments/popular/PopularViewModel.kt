package com.example.movieappazedunet.presentation.ui.fragments.popular

import androidx.lifecycle.viewModelScope
import com.example.movieappazedunet.domain.useCase.PopularMovieUseCase
import com.example.movieappazedunet.domain.useCase.SearchMoviesUseCase
import com.example.movieappazedunet.presentation.util.Constants.API_KEY
import com.example.movieappazedunet.presentation.util.Constants.LANGUAGE
import com.example.movieappazedunet.presentation.util.Constants.MAX_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) :
    com.example.movieappazedunet.presentation.base.mvi_base.BaseViewModel<PopularMoviesIntent, PopularMoviesState>() {

    private fun getPopularMovies(page: Int, clearAllData: Boolean) {
        viewModelScope.launch {
            flow {
                emit(popularMovieUseCase.execute(API_KEY, LANGUAGE, page))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion { loading(false) }
                .catch { errorLiveData.value = it }
                .collect {
                    mState.value =
                        PopularMoviesState.PopularMoviesResult(it, clearAllData, page == MAX_PAGE)
                }
        }
    }

    private fun searchMovies(query: String, page: Int, clearAllData: Boolean) {
        viewModelScope.launch {
            flow {
                emit(searchMoviesUseCase.execute(API_KEY, query, page))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion { loading(false) }
                .catch { errorLiveData.value = it }
                .collect {
                    mState.value = PopularMoviesState.PopularMoviesResult(it, clearAllData,page == MAX_PAGE)
                }
        }
    }

    override fun handleIntent(intent: PopularMoviesIntent) {
        when (intent) {
            is PopularMoviesIntent.FetchPopularMovies -> getPopularMovies(
                intent.page,
                intent.clearAllData
            )
            is PopularMoviesIntent.SearchMovies -> searchMovies(
                intent.query,
                intent.page,
                intent.clearAllData
            )
        }
    }

}