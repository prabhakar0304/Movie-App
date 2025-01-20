package com.example.moviesapp.network

import com.example.moviesapp.model.ItemDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemDetailApiService {
    @GET("title/{id}/details/")
    suspend fun getItemDetails(
        @Path("id") itemId: Int,
        @Query("apiKey") apiKey: String = "qRktjxZ8uZSFDqQybboZL8vqEDfmU22qKMuGTTLP"
    ): ItemDetail
}