package com.example.moviefinder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefinder.domain.Movie

class DetailsViewModel(movie: Movie) : ViewModel() {

    private var _selectedMultimedia = MutableLiveData<Movie>()
    val selectedMultimedia: LiveData<Movie>
        get() = _selectedMultimedia

    init {
        _selectedMultimedia.value = movie
    }
}