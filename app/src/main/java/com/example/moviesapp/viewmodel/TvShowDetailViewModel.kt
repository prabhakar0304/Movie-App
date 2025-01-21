package com.example.moviesapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.ItemDetail
import com.example.moviesapp.network.ItemDetailApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

// Annotates the ViewModel class to enable dependency injection with Hilt.
@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvShowsDetailApiService: ItemDetailApiService, // API service to fetch TV show details
    @ApplicationContext private val context: Context // Application context for displaying Toast messages
) : ViewModel() {

    // MutableState to hold the fetched TV show details
    private val _tvShowDetail = mutableStateOf<ItemDetail?>(null)
    val tvShowDetail: State<ItemDetail?> = _tvShowDetail

    //Fetches detailed information about a specific TV show from the API
    fun fetchTvShowDetail(tvShowId: Int) {
        viewModelScope.launch {
            try {
                // Make an API call to fetch TV show details
                val response = tvShowsDetailApiService.getItemDetails(tvShowId)
                _tvShowDetail.value = response // Update the state with the fetched details
            } catch (e: Exception) {
                // Show a toast message in case of an error
                Toast.makeText(
                    context,
                    "Failed to fetch TV show details. Please try again later.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}