package com.example.movieappazedunet.presentation.ui.activity

import androidx.lifecycle.ViewModelProvider
import com.example.movieappazedunet.R
import com.example.movieappazedunet.databinding.ActivityMainBinding
import com.example.movieappazedunet.presentation.base.mvi_base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainIntent, MainState, ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }
}