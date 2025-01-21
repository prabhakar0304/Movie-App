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
class MovieDetailViewModel @Inject constructor(
    private val moviesDetailApiService: ItemDetailApiService, // API service for fetching movie details
    @ApplicationContext private val context: Context // Application context for showing toast messages
) : ViewModel() {

    // MutableState to hold the movie details fetched from the API
    private val _movieDetail = mutableStateOf<ItemDetail?>(null)
    val movieDetail: State<ItemDetail?> = _movieDetail

    //Fetches detailed information about a specific movie from the API
    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                // Make an API call to fetch the movie details
                val response = moviesDetailApiService.getItemDetails(movieId)
                _movieDetail.value = response // Update the state with the fetched data
            } catch (e: Exception) {
                // Show a toast message in case of an error
                Toast.makeText(
                    context,
                    "Failed to fetch movie details.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
