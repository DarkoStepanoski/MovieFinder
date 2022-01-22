package com.example.moviefinder.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Factory for constructing TrendingViewModel with parameter
 */
class TrendingViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrendingViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}