package com.example.moviesapp.network

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * A class to provide an instance of the `ItemDetailApiService` using Retrofit.
 * This class leverages dependency injection for Retrofit initialization.
 *
 * @constructor Injects a pre-configured `Retrofit` instance.
 */
class MoviesDetailRetrofitInstance @Inject constructor(
    private val retrofit: Retrofit // Injected Retrofit instance for API service creation
) {
    // Initializes the `ItemDetailApiService` using the injected Retrofit instance
    val api: ItemDetailApiService = retrofit.create(ItemDetailApiService::class.java)
}