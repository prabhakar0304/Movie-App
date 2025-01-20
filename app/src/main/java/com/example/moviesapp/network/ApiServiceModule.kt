package com.example.moviesapp.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideItemDetailApiService(retrofit: Retrofit): ItemDetailApiService {
        return retrofit.create(ItemDetailApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesListApiService(retrofit: Retrofit): MoviesListApiService {
        return retrofit.create(MoviesListApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTvShowsListApiService(retrofit: Retrofit): TvShowsListApiService {
        return retrofit.create(TvShowsListApiService::class.java)
    }
}