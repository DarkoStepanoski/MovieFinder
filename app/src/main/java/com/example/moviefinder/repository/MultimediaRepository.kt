package com.example.moviefinder.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moviefinder.database.MoviesDatabase
import com.example.moviefinder.database.asDomainModel
import com.example.moviefinder.domain.Movie
import com.example.moviefinder.network.Network
import com.example.moviefinder.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MultimediaRepository(private val database: MoviesDatabase) {
    /**
     * Movies that can be shown on the screen.
     */
    val movies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getMovies()) {
        it.asDomainModel()
    }

    /**
     * Refresh the movies stored in the offline cache.
     *
     * To actually load the movies for use we observe [movies]
     */
    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            database.movieDao.clear()
            try {
                val trendingMovies = Network.retrofitService.getTrendingMovies().await()
                database.movieDao.insertAll(*trendingMovies.asDatabaseModel())
            } catch (e: Exception) {
                Log.e("MultimediaRepository", "Error: $e ")
            }
        }
    }

    /**
     * Searches for a movie with search query.
     *
     * To load the movies for use we observe [movies]
     */
    suspend fun searchForMovies(searchQuery: String) {
        withContext(Dispatchers.IO) {
            database.movieDao.clear()
            try {
                val searchedMovies = Network.retrofitService.getSearchedMovies(searchQuery).await()
                database.movieDao.insertAll(*searchedMovies.asDatabaseModel())
            } catch (e: Exception) {
                Log.e("MultimediaRepository", "Error: $e ")
            }
        }
    }
}