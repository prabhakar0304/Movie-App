package com.example.moviesapp.network

import com.example.moviesapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to define API endpoints for fetching the movies list.
 * Uses Retrofit annotations to configure HTTP requests.
 */
interface MoviesListApiService {

    /**
     * Fetches a list of movies based on the provided query parameters.
     *
     * @param apiKey API key for authentication (default value provided).
     * @param types Specifies the type of content to fetch (default is "movie").
     * @param sortBy Sorting criteria (default is "popularity_desc" to fetch movies sorted by popularity).
     * @param releaseDateStart Start date for filtering movies, as a Unix timestamp (default: Jan 1, 2021).
     * @param releaseDateEnd End date for filtering movies, as a Unix timestamp (default: Jan 1, 2025).
     * @return An `ApiResponse` object containing the list of movies.
     */
    @GET("list-titles/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String = "qRktjxZ8uZSFDqQybboZL8vqEDfmU22qKMuGTTLP", // API Key for authentication
        @Query("types") types: String = "movie", // Specify to fetch movies only
        @Query("sort_by") sortBy: String = "popularity_desc", // Sorting criterion: by popularity in descending order
        @Query("release_date_start") releaseDateStart: Long = 1609459200, // Start date filter (Jan 1, 2021)
        @Query("release_date_end") releaseDateEnd: Long = 1737072000 // End date filter (Jan 1, 2025)
    ): ApiResponse
}