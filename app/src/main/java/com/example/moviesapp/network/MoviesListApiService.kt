package com.example.moviesapp.network

import com.example.moviesapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListApiService {
    @GET("list-titles/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String = "qRktjxZ8uZSFDqQybboZL8vqEDfmU22qKMuGTTLP", // API Key
        @Query("types") types: String = "movie", // Fetch movies only
        @Query("sort_by") sortBy: String = "popularity_desc", // Sort by popularity
        @Query("release_date_start") releaseDateStart: Long = 1609459200, // Example start timestamp (Jan 1, 2021)
        @Query("release_date_end") releaseDateEnd: Long = 1737072000 // Example end timestamp (Jan 1, 2025)
    ): ApiResponse
}