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

@Composable
fun TvShowsScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val tvShows = homeViewModel.tvShows.value // Fetch TV shows from HomeViewModel

    // Place the NavHost inside the composable function
    NavHost(
        navController = navController,
        startDestination = "tv_shows_list"
    ) {
        // Tv Show List Screen
        composable("tv_shows_list") {
            TvShowListScreen(
                tvShows = tvShows,
                onTvShowClick = { tvShowId ->
                    navController.navigate("tv_show_detail/$tvShowId")
                }
            )
        }
        // Tv Show Detail Screen
        composable(
            route = "tv_show_detail/{tvShowId}",
            arguments = listOf(navArgument("tvShowId") { type = NavType.IntType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getInt("tvShowId") ?: 0
            TvShowDetailScreen(tvShowId = tvShowId)
        }
    }
}


@Composable
fun TvShowListScreen(
    tvShows: List<Item>,
    onTvShowClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (tvShows.isEmpty()) {
            LazyColumn {
                items(12) { // Display 12 shimmer placeholders
                    ShimmerTvShowItem()
                }
            }
        } else {
            LazyColumn {
                items(tvShows) { tvShow ->
                    TvShowItem(tvShow = tvShow, onTvShowClick = onTvShowClick)
                }
            }
        }
    }
}


@Composable
fun TvShowItem(tvShow: Item, onTvShowClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTvShowClick(tvShow.id) },
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
                Text(text = tvShow.title, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "${tvShow.year}")
            }
        }
    }
}


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
