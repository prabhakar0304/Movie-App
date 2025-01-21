package com.example.moviesapp.network

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * This class provides an instance of the `ItemDetailApiService` specifically for TV show details.
 * It uses the injected `Retrofit` instance to create the API service.
 */
class TvShowsDetailRetrofitInstance @Inject constructor(
    private val retrofit: Retrofit // Injected Retrofit instance
) {
    // API service for fetching TV show details
    val api: ItemDetailApiService = retrofit.create(ItemDetailApiService::class.java)
}