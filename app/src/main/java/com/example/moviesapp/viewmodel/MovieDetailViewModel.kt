package com.example.moviesapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.ItemDetail
import com.example.moviesapp.network.ItemDetailApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesDetailApiService: ItemDetailApiService
) : ViewModel() {

    private val _movieDetail = mutableStateOf<ItemDetail?>(null)
    val movieDetail: State<ItemDetail?> = _movieDetail

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = moviesDetailApiService.getItemDetails(movieId)
                _movieDetail.value = response
            } catch (e: Exception) {
                // Handle errors (log or show UI message)
            }
        }
    }
}
