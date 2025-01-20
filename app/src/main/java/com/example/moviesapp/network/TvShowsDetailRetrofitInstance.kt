package com.example.moviesapp.network

import retrofit2.Retrofit
import javax.inject.Inject

class TvShowsDetailRetrofitInstance @Inject constructor(
    private val retrofit: Retrofit
) {
    val api: ItemDetailApiService = retrofit.create(ItemDetailApiService::class.java)
}
