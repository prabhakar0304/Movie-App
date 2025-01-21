package com.example.moviesapp.network

import com.example.moviesapp.model.ItemDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Define a Retrofit API service for fetching item details
interface ItemDetailApiService {

    /**
     * Fetch item details based on the provided item ID.
     *
     * @param itemId The ID of the item whose details are to be fetched.
     * @param apiKey The API key required for authentication. A default value is provided.
     * @return An instance of [ItemDetail] containing detailed information about the item.
     */
    @GET("title/{id}/details/") // API endpoint with a path parameter for the item ID
    suspend fun getItemDetails(
        @Path("id") itemId: Int, // Maps the 'itemId' to the 'id' path in the endpoint
        @Query("apiKey") apiKey: String = "qRktjxZ8uZSFDqQybboZL8vqEDfmU22qKMuGTTLP" // Passes the API key as a query parameter
    ): ItemDetail
}