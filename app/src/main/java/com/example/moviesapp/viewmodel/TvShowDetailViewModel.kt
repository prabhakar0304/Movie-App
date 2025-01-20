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
class TvShowDetailViewModel @Inject constructor(
    private val tvShowsDetailApiService: ItemDetailApiService
) : ViewModel() {

    private val _tvShowDetail = mutableStateOf<ItemDetail?>(null)
    val tvShowDetail : State<ItemDetail?> = _tvShowDetail

    fun fetchTvShowDetail(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val response = tvShowsDetailApiService.getItemDetails(tvShowId)
                _tvShowDetail.value = response
            } catch (e: Exception) {
                // Handle errors (log or show UI message)
            }
        }
    }
}