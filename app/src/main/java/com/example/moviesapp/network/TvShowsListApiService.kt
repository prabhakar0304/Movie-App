package com.example.moviesapp.network

import com.example.moviesapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for fetching TV shows list.
 * Defines an API call to retrieve TV shows data.
 */
interface TvShowsListApiService {
    /**
     * Fetches a list of TV shows from the API.
     *
     * @param apiKey The API key for authentication (default value provided).
     * @param types Specifies the type of content to fetch, e.g., "tv_series".
     * @param sortBy Sorts the results by the given criteria, e.g., "popularity_desc".
     * @param releaseDateStart The start date (in Unix timestamp) to filter TV shows by release date.
     * @param releaseDateEnd The end date (in Unix timestamp) to filter TV shows by release date.
     * @return An ApiResponse object containing the list of TV shows.
     */
    @GET("list-titles/")
    suspend fun getTvShows(
        @Query("apiKey") apiKey: String = "qRktjxZ8uZSFDqQybboZL8vqEDfmU22qKMuGTTLP", // API Key
        @Query("types") types: String = "tv_series", // Fetch TV series only
        @Query("sort_by") sortBy: String = "popularity_desc", // Sort by popularity in descending order
        @Query("release_date_start") releaseDateStart: Long = 1609459200, // Start date filter (Jan 1, 2021)
        @Query("release_date_end") releaseDateEnd: Long = 1737072000 // End date filter (Jan 1, 2025)
    ): ApiResponse
}