package com.example.moviesapp.view


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviesapp.animation.ShimmerEffect
import com.example.moviesapp.model.Item
import com.example.moviesapp.viewmodel.HomeViewModel


/**
 * Main screen for displaying the list of TV shows and handling navigation.
 *
 * @param homeViewModel The ViewModel responsible for providing TV show data.
 */
@Composable
fun TvShowsScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController() // Navigation controller for managing screen transitions
    val tvShows = homeViewModel.tvShows.value // Fetch TV shows from HomeViewModel

    // Navigation Host for handling navigation between screens
    NavHost(
        navController = navController,
        startDestination = "tv_shows_list"
    ) {
        // TV Shows List Screen
        composable("tv_shows_list") {
            TvShowListScreen(
                tvShows = tvShows,
                onTvShowClick = { tvShowId ->
                    navController.navigate("tv_show_detail/$tvShowId") // Navigate to detail screen
                }
            )
        }
        // TV Show Detail Screen
        composable(
            route = "tv_show_detail/{tvShowId}",
            arguments = listOf(navArgument("tvShowId") { type = NavType.IntType }) // Argument for TV show ID
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getInt("tvShowId") ?: 0 // Retrieve TV show ID
            TvShowDetailScreen(tvShowId = tvShowId)
        }
    }
}

/**
 * Screen displaying a list of TV shows or a shimmer effect when loading.
 *
 * @param tvShows List of TV shows to display.
 * @param onTvShowClick Callback when a TV show is clicked.
 */
@Composable
fun TvShowListScreen(
    tvShows: List<Item>,
    onTvShowClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(), // Fill the available screen space
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (tvShows.isEmpty()) { // Show shimmer effect while loading
            LazyColumn {
                items(12) { // Display 12 shimmer placeholders
                    ShimmerTvShowItem()
                }
            }
        } else { // Display the list of TV shows
            LazyColumn {
                items(tvShows) { tvShow ->
                    TvShowItem(tvShow = tvShow, onTvShowClick = onTvShowClick)
                }
            }
        }
    }
}

/**
 * Composable displaying a single TV show item in the list.
 *
 * @param tvShow The TV show to display.
 * @param onTvShowClick Callback when the item is clicked.
 */
@Composable
fun TvShowItem(tvShow: Item, onTvShowClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTvShowClick(tvShow.id) }, // Handle click event
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp) // Inner padding for the card content
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = tvShow.title, modifier = Modifier.padding(bottom = 4.dp)) // Show title
                Text(text = "${tvShow.year}") // Show year
            }
        }
    }
}

/**
 * Composable showing a shimmer effect for TV show items while loading.
 */
@Composable
fun ShimmerTvShowItem() {
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
            // Column for shimmer placeholders
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Placeholder for the title
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // Placeholder width (60% of the screen width)
                        .height(20.dp) // Placeholder height
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize()) // Apply shimmer effect
                }

                Spacer(modifier = Modifier.height(8.dp)) // Space between placeholders

                // Placeholder for the year
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f) // Placeholder width (40% of the screen width)
                        .height(16.dp) // Placeholder height
                ) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize()) // Apply shimmer effect
                }
            }
        }
    }
}