package com.example.movieappazedunet.presentation.ui.activity

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    com.example.movieappazedunet.presentation.base.mvi_base.BaseViewModel<MainIntent, MainState>() {
    override fun handleIntent(intent: MainIntent) {

    }
}