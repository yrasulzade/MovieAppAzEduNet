package com.example.movieappazedunet.presentation.util

import androidx.core.widget.NestedScrollView

abstract class NestedScrollPageListener : NestedScrollView.OnScrollChangeListener {
    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
            if (!isLoading && !lastPageCalled) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract val lastPageCalled: Boolean
    abstract val isLoading: Boolean
}