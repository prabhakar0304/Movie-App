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

@Composable
fun MoviesScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val movies = homeViewModel.movies.value // Fetch movies from HomeViewModel

    // Navigation Host for navigation between list and detail screens
    NavHost(
        navController = navController,
        startDestination = "movies_list"
    ) {
        composable("movies_list") {
            MovieListScreen(
                movies = movies,
                onMovieClick = { movieId ->
                    // Navigate to the detail screen with the movieId
                    navController.navigate("movie_detail/$movieId")
                }
            )
        }
        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId)
        }
    }
}

@Composable
fun MovieListScreen(
    movies: List<Item>,
    onMovieClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Show shimmer effect if movies are not loaded yet
        if (movies.isEmpty()) {
            LazyColumn {
                items(12) { // Display 12 shimmer placeholders
                    ShimmerMovieItem()
                }
            }
        } else {
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie = movie, onMovieClick = onMovieClick)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Item, onMovieClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMovieClick(movie.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = movie.title, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "${movie.year}")
            }
        }
    }
}

@Composable
fun ShimmerMovieItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Shimmer placeholder for the title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Placeholder width (60% of the screen width)
                        .height(20.dp) // Placeholder height
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Shimmer placeholder for the year
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f) // Placeholder width (40% of the screen width)
                        .height(16.dp) // Placeholder height
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}