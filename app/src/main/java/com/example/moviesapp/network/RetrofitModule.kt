package com.example.moviesapp.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module to provide Retrofit instance for network calls.
 * Ensures dependency injection setup for Retrofit throughout the app.
 */
@Module
@InstallIn(SingletonComponent::class) // Install this module in the SingletonComponent scope
object RetrofitModule {

    private const val BASE_URL = "https://api.watchmode.com/v1/" // Base URL for the API

    /**
     * Provides a singleton Retrofit instance for making network requests.
     *
     * @return A configured Retrofit instance with the base URL and Gson converter.
     */
    @Provides
    @Singleton // Ensures a single instance of Retrofit is used across the app
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Set the base URL for API calls
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter to handle JSON
            .build() // Build the Retrofit instance
    }
}