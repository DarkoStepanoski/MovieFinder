package com.example.moviefinder.network

import com.example.moviefinder.database.DatabaseMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(
    val page: Int,
    val results: List<NetworkMovie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name ="total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    val id: Int,
    val title: String?,
    val overview: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "vote_count") val voteCount: Int?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "release_date") val releaseDate: String?,
)

/**
 * Extension function that converts from network objects to database objects
 */
fun NetworkMovieContainer.asDatabaseModel(): Array<DatabaseMovie> {
    return results.map {

        val checkedPosterPath: String
        val checkedBackdropPath: String
        val checkedReleaseDate: String
        val checkedTitle: String
        val checkedOverview: String
        val checkedOriginalLanguage: String
        val checkedVoteAverage: Double
        val checkedVoteCount: Int

        /**
         * For some of the values we can get null, we are checking that here.
         */
        if (it.posterPath != null){
            checkedPosterPath = it.posterPath
        } else checkedPosterPath = ""

        if (it.backdropPath != null){
            checkedBackdropPath = it.backdropPath
        } else checkedBackdropPath = ""

        if (it.releaseDate != null){
            checkedReleaseDate = it.releaseDate
        } else checkedReleaseDate = ""

        if (it.title != null){
            checkedTitle = it.title
        } else checkedTitle = ""

        if (it.overview != null){
            checkedOverview = it.overview
        } else checkedOverview = ""

        if (it.originalLanguage != null){
            checkedOriginalLanguage = it.originalLanguage
        } else checkedOriginalLanguage = ""

        if (it.voteAverage != null){
            checkedVoteAverage = it.voteAverage
        } else checkedVoteAverage = 0.0

        if (it.voteCount != null){
            checkedVoteCount = it.voteCount
        } else checkedVoteCount = 0

        DatabaseMovie(
            idKey = 0,
            id = it.id,
            title = checkedTitle,
            overview = checkedOverview,
            posterPath = checkedPosterPath,
            backdropPath = checkedBackdropPath,
            voteAverage = checkedVoteAverage,
            voteCount = checkedVoteCount,
            originalLanguage = checkedOriginalLanguage,
            releaseDate = checkedReleaseDate)
    }.toTypedArray()
}
