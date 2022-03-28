package com.example.movieappazedunet.presentation.ui.fragments.popular

import android.annotation.SuppressLint
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappazedunet.R
import com.example.movieappazedunet.domain.model.movie.Result
import com.example.movieappazedunet.databinding.FragmentPopularMoviesBinding
import com.example.movieappazedunet.presentation.adapter.PopularAdapter
import com.example.movieappazedunet.presentation.base.mvi_base.BaseMviFragment
import com.example.movieappazedunet.presentation.util.NestedScrollPageListener
import com.example.movieappazedunet.presentation.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment :
    BaseMviFragment<PopularMoviesIntent, PopularMoviesState, FragmentPopularMoviesBinding, PopularViewModel>(),
    PopularAdapter.OnPopularMovieClick {
    val TAG = "PopularMoviesFragment"
    private lateinit var viewModel: PopularViewModel
    private lateinit var adapter: PopularAdapter
    private val movies = ArrayList<Result>()
    private var loading = false
    private var isLastPage = false
    private var page = 1
    private var query = ""
    private var clearAllData = false
    private var observeDataFlow = true

    override fun getViewModel(): PopularViewModel {
        viewModel = ViewModelProvider(this)[PopularViewModel::class.java]
        return viewModel
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_popular_movies
    }

    override fun initUI() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        initAdapter()

        viewDataBinding.searchMovies.setOnDebounceTextWatcher(lifecycle) { input ->
            Log.d(TAG, "initUI: $observeDataFlow")
            if (observeDataFlow) {

                page = 1
                query = input

                if (input.isEmpty()) {
                    baseActivity.hideKeyboard()
                    viewDataBinding.searchMovies.isFocusableInTouchMode = false
                    viewDataBinding.searchMovies.clearFocus()
                    viewDataBinding.searchMovies.isFocusableInTouchMode = true
                    dispatchIntent(PopularMoviesIntent.FetchPopularMovies(page, true))
                } else {
                    dispatchIntent(PopularMoviesIntent.SearchMovies(query, page, true))
                }
            } else {
                observeDataFlow = true
            }
        }
    }

    override fun initEVENT() {
        dispatchIntent(PopularMoviesIntent.FetchPopularMovies(page, false))

        viewDataBinding.scrollView.setOnScrollChangeListener(object :
            NestedScrollPageListener() {
            override fun loadMoreItems() {
                page += 1

                if (viewDataBinding.searchMovies.isFocused) {
                    dispatchIntent(
                        PopularMoviesIntent.SearchMovies(
                            query, page, false
                        )
                    )
                } else {
                    dispatchIntent(PopularMoviesIntent.FetchPopularMovies(page, false))
                }
            }

            override val lastPageCalled: Boolean
                get() = isLastPage
            override val isLoading: Boolean
                get() = loading
        })
    }

    override fun render(state: PopularMoviesState) {
        when (state) {
            is PopularMoviesState.PopularMoviesResult -> updateRecyclerView(
                state.popularMovies.results,
                state.clearAllData,
                state.isLastPage
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView(results: List<Result>, clearAllData: Boolean, lastPage: Boolean) {
        this.clearAllData = clearAllData
        this.isLastPage = lastPage

        if (clearAllData) {
            this.movies.clear()
            viewDataBinding.scrollView.scrollTo(0, 0)
        }

        movies.addAll(results)
        adapter.notifyDataSetChanged()

        Log.d(TAG, "updateRecyclerView: ${movies.size} ${results.size} $clearAllData")
    }

    private fun initAdapter() {
        adapter = PopularAdapter(requireContext(), this, movies)
        val layoutManager = LinearLayoutManager(context)
        viewDataBinding.recyclerView.layoutManager = layoutManager
        viewDataBinding.recyclerView.adapter = adapter
        viewDataBinding.recyclerView.setHasFixedSize(true)
    }

    override fun onMovieClick(movieId: Long) {
        safeNavigate(
            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(
                movieId
            )
        )
    }

    override fun onPause() {
        super.onPause()
        observeDataFlow = false
    }
}