package com.example.moviefinder.network

import com.example.moviefinder.util.Constants.Companion.API_KEY
import com.example.moviefinder.util.Constants.Companion.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MultimediaService {
    /**
     * A retrofit service to fetch trending Movies from the past week
     */
    @GET("trending/movie/week")
    fun getTrendingMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ): Deferred<NetworkMovieContainer>

    /**
     * A retrofit service for searching Movies
     */
    @GET("search/movie")
    fun getSearchedMovies(
        @Query("query")
        searchQuery: String,
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        pageNumber: Int = 1
    ) : Deferred<NetworkMovieContainer>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object Network{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService: MultimediaService by lazy { retrofit.create(MultimediaService::class.java) }
}