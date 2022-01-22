package com.example.moviefinder.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Movie object for use within our app, for displaying on screen etc.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse network calls
 */
@Parcelize
data class Movie(
    val idKey: Long,
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originalLanguage: String,
    val releaseDate: String): Parcelable