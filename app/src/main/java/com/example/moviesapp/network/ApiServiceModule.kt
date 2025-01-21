package com.example.moviesapp.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

// Define a Dagger module to provide API services for dependency injection
@Module
@InstallIn(SingletonComponent::class) // Install this module in the application-level component
object ApiServiceModule {

    // Provide the ItemDetailApiService instance using Retrofit
    @Provides
    @Singleton // Ensure only one instance is created (singleton scope)
    fun provideItemDetailApiService(retrofit: Retrofit): ItemDetailApiService {
        return retrofit.create(ItemDetailApiService::class.java) // Create API service implementation
    }

    // Provide the MoviesListApiService instance using Retrofit
    @Provides
    @Singleton
    fun provideMoviesListApiService(retrofit: Retrofit): MoviesListApiService {
        return retrofit.create(MoviesListApiService::class.java)
    }

    // Provide the TvShowsListApiService instance using Retrofit
    @Provides
    @Singleton
    fun provideTvShowsListApiService(retrofit: Retrofit): TvShowsListApiService {
        return retrofit.create(TvShowsListApiService::class.java)
    }
}