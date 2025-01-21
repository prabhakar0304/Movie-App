package com.example.moviesapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.animation.ShimmerEffect
import com.example.moviesapp.model.Item
import com.example.moviesapp.viewmodel.HomeViewModel

// Composable to display the MoviesScreen
@Composable
fun MoviesScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController() // Remember navigation controller for navigation between screens
    val movies = homeViewModel.movies.value // Fetch movies from HomeViewModel

    // Navigation Host to manage navigation between different screens
    NavHost(
        navController = navController,
        startDestination = "movies_list" // Starting screen is the movie list
    ) {
        // Define composable for movies list screen
        composable("movies_list") {
            MovieListScreen(
                movies = movies, // Passing movies to the MovieListScreen
                onMovieClick = { movieId ->
                    // Navigate to movie detail screen passing movieId
                    navController.navigate("movie_detail/$movieId")
                }
            )
        }
        // Define composable for movie detail screen
        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType }) // Expect movieId as an argument
        ) { backStackEntry ->
            // Extract movieId from backStackEntry arguments
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId) // Show movie detail screen with given movieId
        }
    }
}

// Composable to display the list of movies
@Composable
fun MovieListScreen(
    movies: List<Item>, // List of movies
    onMovieClick: (Int) -> Unit // Callback for when a movie item is clicked
) {
    Column(
        modifier = Modifier.fillMaxSize(), // Ensure the column fills available space
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // If movies are not loaded, display shimmer effect for placeholders
        if (movies.isEmpty()) {
            LazyColumn {
                items(12) { // Display 12 shimmer placeholders
                    ShimmerMovieItem()
                }
            }
        } else {
            // If movies are loaded, display the list of movie items
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie = movie, onMovieClick = onMovieClick) // Display each movie item
                }
            }
        }
    }
}

// Composable to display individual movie item
@Composable
fun MovieItem(movie: Item, onMovieClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Add padding around the card
            .clickable { onMovieClick(movie.id) }, // Trigger onMovieClick when the card is clicked
        elevation = CardDefaults.cardElevation(4.dp) // Add elevation to the card
    ) {
        Row(
            modifier = Modifier.padding(16.dp) // Add padding inside the card
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ensure column takes up full width
                    .padding(8.dp) // Add padding inside the column
            ) {
                // Movie title
                Text(text = movie.title, modifier = Modifier.padding(bottom = 4.dp))
                // Movie year
                Text(text = "${movie.year}")
            }
        }
    }
}

// Composable to show shimmer placeholders for movie item while data is loading
@Composable
fun ShimmerMovieItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Add padding around the shimmer card
        elevation = CardDefaults.cardElevation(4.dp) // Elevation for the shimmer card
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding inside the shimmer card
        ) {
            // Shimmer effect placeholder for movie title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Placeholder width (60% of screen width)
                        .height(20.dp) // Placeholder height for title
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize()) // Apply shimmer effect
                }

                Spacer(modifier = Modifier.height(8.dp)) // Spacer between placeholders

                // Shimmer effect placeholder for movie year
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f) // Placeholder width (40% of screen width)
                        .height(16.dp) // Placeholder height for year
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize()) // Apply shimmer effect
                }
            }
        }
    }
}