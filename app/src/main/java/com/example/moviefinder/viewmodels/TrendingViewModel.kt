package com.example.moviefinder.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.moviefinder.database.getDatabase
import com.example.moviefinder.domain.Movie
import com.example.moviefinder.repository.MultimediaRepository
import kotlinx.coroutines.*

class TrendingViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val multimediaRepository = MultimediaRepository(database)

    /**
     * Variable that takes query for movie that we want to search.
     * When it is not null or empty we will display that searched movie.
     */
    private var _showSearchMovies = MutableLiveData<String>()
    val showSearchMovies: LiveData<String>
        get() = _showSearchMovies

    /**
     * Variable that tells if Trending Movies should be displayed.
     */
    private var _showTrendintMovies = MutableLiveData<Boolean>()
    val showTrendingMovies: LiveData<Boolean>
        get() = _showTrendintMovies

    /**
     * Variable that tells the fragment whether it should navigate to [DetailsFragment].
     */
    private var _navigateToMultimediaDetails = MutableLiveData<Movie>()
    val navigateToMultimediaDetails: LiveData<Movie>
        get() = _navigateToMultimediaDetails

    init {
        viewModelScope.launch {
            multimediaRepository.refreshMovies()
        }
    }

    /**
     * Movies that will be shown on the screen.
     */
    val showMovies = multimediaRepository.movies

    fun trendingMovies(){
        viewModelScope.launch {
            multimediaRepository.refreshMovies()
        }
    }

    fun showTrendingMovies() {
        _showTrendintMovies.value = true
    }

    fun doneShowingTrendingMovies(){
        _showTrendintMovies.value = false
    }

    fun showSearchedMovies(search: String) {
        viewModelScope.launch {
            multimediaRepository.searchForMovies(search)
        }
    }

    fun doneShowingSearchedMovies(){
        _showSearchMovies.value = null
    }

    fun searchForAMovie(searchQuery: String) {
        _showSearchMovies.value = searchQuery
    }

    fun displayMultimediaDetails(movie: Movie){
        _navigateToMultimediaDetails.value = movie
    }

    fun displayMultimediaDetailsComplete(){
        _navigateToMultimediaDetails.value = null
    }
}