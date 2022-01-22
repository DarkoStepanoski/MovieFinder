package com.example.moviefinder.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviefinder.domain.Movie

@Entity
data class DatabaseMovie constructor(
    @PrimaryKey(autoGenerate = true)
    val idKey: Long = 0L,
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originalLanguage: String,
    val releaseDate: String)

/**
 * Extension function which converts from database objects to domain objects
 */
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            idKey = it.idKey,
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            originalLanguage = it.originalLanguage,
            releaseDate = it.releaseDate)
    }
}