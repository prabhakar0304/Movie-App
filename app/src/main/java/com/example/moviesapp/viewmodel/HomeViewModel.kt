package com.example.moviesapp.viewmodel


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.Item
import com.example.moviesapp.network.MoviesListApiService
import com.example.moviesapp.network.TvShowsListApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Annotates the ViewModel class to enable dependency injection with Hilt.
@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moviesListApiService: MoviesListApiService,  // Injected API service for movies
    private val tvShowsListApiService: TvShowsListApiService // Injected API service for TV shows
) : ViewModel() {

    // StateFlow to track the currently selected screen (Movies or TV Shows).
    private val _selectedScreen = MutableStateFlow(Screen.Movies)
    val selectedScreen: StateFlow<Screen> = _selectedScreen

    // MutableState to store the list of movies fetched from the API.
    private val _movies = mutableStateOf<List<Item>>(emptyList())
    val movies: State<List<Item>> = _movies

    // MutableState to store the list of TV shows fetched from the API.
    private val _tvShows = mutableStateOf<List<Item>>(emptyList())
    val tvShows: State<List<Item>> = _tvShows

    // CompositeDisposable to manage RxJava subscriptions and ensure proper disposal.
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchMoviesAndTvShows() // Fetch data when ViewModel is created.
    }

    // Function to toggle between Movies and TV Shows screens.
    fun toggleScreen(screen: Screen) {
        _selectedScreen.value = screen
    }

    // Function to fetch movies and TV shows from their respective APIs concurrently.
    private fun fetchMoviesAndTvShows() {

        viewModelScope.launch {
            try {
                // Perform API calls concurrently using async and Dispatchers.IO for background execution.
                val moviesDeferred = async(Dispatchers.IO) { moviesListApiService.getMovies() }
                val tvShowsDeferred = async(Dispatchers.IO) { tvShowsListApiService.getTvShows() }

                // Await the results of both API calls.
                val moviesResponse = moviesDeferred.await()
                val tvShowsResponse = tvShowsDeferred.await()

                // Update state with the fetched data, or fallback to an empty list if null.
                _movies.value = moviesResponse.titles
                _tvShows.value = tvShowsResponse.titles

            } catch (error: Exception) {
                // Show a toast message in case of an error
                Toast.makeText(
                    // Assuming 'getApplication()' or 'context' is available for Toast
                    context,
                    "Unable to fetch data.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // Dispose of all RxJava subscriptions when the ViewModel is cleared.
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    // Enum to define the possible screens in the app.
    enum class Screen {
        Movies,
        TVShows
    }
}