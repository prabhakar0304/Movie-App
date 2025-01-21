package com.example.moviesapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.moviesapp.viewmodel.MovieDetailViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import com.example.moviesapp.animation.ShimmerEffect
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MovieDetailScreen(
    movieId: Int, // ID of the movie to display details for
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel() // ViewModel to fetch movie details
) {
    // Fetch movie details from ViewModel when the screen is first loaded
    LaunchedEffect(movieId) {
        movieDetailViewModel.fetchMovieDetail(movieId)
    }

    val movieDetail = movieDetailViewModel.movieDetail.value

    // States to track image and data loading statuses
    var isImageLoaded by remember { mutableStateOf(false) }
    var isDataLoaded by remember { mutableStateOf(false) }

    // Update data loading state when movie details are available
    LaunchedEffect(movieDetail) {
        if (movieDetail != null) {
            isDataLoaded = true
        }
    }

    // Column layout to arrange movie details in a scrollable manner
    Column(
        modifier = Modifier
            .fillMaxSize() // Ensure the Column fills the available screen space
            .verticalScroll(rememberScrollState()) // Make the column scrollable
            .padding(16.dp), // Padding around the content
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        movieDetail?.let { movie -> // Only proceed if movie details are available

            // Movie Poster Section with shimmer effect while loading
            Box(
                modifier = Modifier
                    .height(500.dp) // Fixed height for the image box
                    .padding(bottom = 16.dp) // Bottom padding
            ) {
                if (!isImageLoaded) {
                    // Show shimmer effect while image is loading
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }

                // Load the movie poster asynchronously using Coil
                AsyncImage(
                    model = movie.posterLarge, // Poster image URL
                    contentDescription = "Movie Poster", // Content description for accessibility
                    modifier = Modifier
                        .fillMaxHeight() // Fill the height of the box
                        .clip(RoundedCornerShape(16.dp)), // Apply rounded corners
                    contentScale = ContentScale.Crop, // Crop image to fit the box
                    onSuccess = {
                        isImageLoaded = true // Update image loaded state on success
                    }
                )
            }

            // Movie Title Section with shimmer effect while loading
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Make title fill width
                    .height(40.dp) // Fixed height for title area
                    .padding(bottom = 8.dp) // Bottom padding
            ) {
                if (isDataLoaded) {
                    // Show title once data is loaded
                    Text(
                        text = movie.title, // Display movie title
                        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium, // Title style
                        textAlign = TextAlign.Center, // Center-align the title
                        modifier = Modifier.fillMaxWidth() // Make text fill width
                    )
                } else {
                    // Show shimmer effect while title is loading
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
            }

            // Row for displaying release date and rating with shimmer effect while loading
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Make the row fill width
                    .padding(bottom = 16.dp), // Bottom padding for the row
                horizontalArrangement = Arrangement.SpaceBetween, // Space out content
                verticalAlignment = Alignment.CenterVertically // Align content vertically
            ) {

                // Release Date Box with shimmer effect while loading
                Box(
                    modifier = Modifier
                        .width(200.dp) // Fixed width for release date box
                        .height(20.dp) // Fixed height for box
                ) {
                    if (isDataLoaded) {
                        // Format and display release date
                        val releaseDateText = movie.release_date.let {
                            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                            dateFormat.format(it) // Format Date to String
                        } ?: "Release: Not available" // Default text if release date is null

                        Text(
                            text = "Release : $releaseDateText", // Display release date
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        // Show shimmer effect while release date is loading
                        ShimmerEffect(modifier = Modifier.fillMaxSize())
                    }
                }

                // Rating Box with shimmer effect while loading
                Box(
                    modifier = Modifier
                        .width(120.dp) // Fixed width for rating box
                        .height(20.dp) // Fixed height for box
                ) {
                    if (isDataLoaded) {
                        // Display user rating
                        Text(
                            text = "Rating: ${movie.user_rating} / 10", // Show rating
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        // Show shimmer effect while rating is loading
                        ShimmerEffect(modifier = Modifier.fillMaxSize())
                    }
                }
            }

            // Description Section with shimmer effect while loading
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Make description fill width
                    .wrapContentHeight() // Allow height to adjust based on content size
            ) {
                if (isDataLoaded) {
                    // Show movie description once data is loaded
                    Text(
                        text = movie.plot_overview, // Movie description text
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium, // Description style
                        textAlign = TextAlign.Justify // Justify text for a better layout
                    )
                } else {
                    // Show shimmer effect while description is loading
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

