package com.example.moviesapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.ApiResponse
import com.example.moviesapp.model.Item
import com.example.moviesapp.network.MoviesListApiService

import com.example.moviesapp.network.TvShowsListApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val apiRepository: ApiRepository
    private val moviesListApiService: MoviesListApiService,  // Inject the API service
    private val tvShowsListApiService: TvShowsListApiService
) : ViewModel() {

    private val _selectedScreen = MutableStateFlow(Screen.Movies)
    val selectedScreen: StateFlow<Screen> = _selectedScreen

    // Function to toggle between screens
    fun toggleScreen(screen: Screen) {
        _selectedScreen.value = screen
    }

    private val _movies = mutableStateOf<List<Item>>(emptyList())
    val movies: State<List<Item>> = _movies

    private val _tvShows = mutableStateOf<List<Item>>(emptyList())
    val tvShows: State<List<Item>> = _tvShows

    private var disposable: Disposable? = null  // Store the Disposable

    private val compositeDisposable = CompositeDisposable()

    init {
        Log.d("manual_debug", "line1")
        fetchMoviesAndTvShows()
    }

    private fun fetchMoviesAndTvShows() {
        Log.d("manual_debug", "line2")

        viewModelScope.launch {
            try {
                // Perform the API calls in parallel using async
                val moviesDeferred = async(Dispatchers.IO) { moviesListApiService.getMovies() }
                val tvShowsDeferred = async(Dispatchers.IO) { tvShowsListApiService.getTvShows() }

                // Wait for both API calls to complete
                val moviesResponse = moviesDeferred.await()
                val tvShowsResponse = tvShowsDeferred.await()

//                Log.d("manual_debug", "line3")
//                Log.d("manual_debug", moviesResponse.toString())
//                Log.d("manual_debug", tvShowsResponse.toString())

                // Update the state with the data received
                _movies.value = moviesResponse.titles ?: emptyList()
                _tvShows.value = tvShowsResponse.titles ?: emptyList()
            } catch (error: Exception) {
                // Handle the error gracefully
                Log.e("manual_debug", error.message ?: "Unknown error")
            }
        }
    }


    // Dispose of the subscription when ViewModel is cleared
//    override fun onCleared() {
//        super.onCleared()
//        disposable?.dispose()
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear() // Dispose of all subscriptions
    }

    enum class Screen {
        Movies,
        TVShows
    }
}
