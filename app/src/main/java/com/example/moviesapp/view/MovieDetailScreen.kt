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
import androidx.compose.foundation.shape.CircleShape
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
    movieId: Int,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    // Fetch the movie details when the screen loads
    LaunchedEffect(movieId) {
        movieDetailViewModel.fetchMovieDetail(movieId)
    }

    val movieDetail = movieDetailViewModel.movieDetail.value

    var isImageLoaded by remember { mutableStateOf(false) }
    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(movieDetail) {
        if (movieDetail != null) {
            isDataLoaded = true
        }
    }

    // Wrapping everything in a scrollable container to avoid content being cut off
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Ensures the content is scrollable
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        movieDetail?.let { movie ->

            // Movie Poster with shimmer effect while loading
            Box(
                modifier = Modifier
                    .height(500.dp) // Fixed height for the box
                    .padding(bottom = 16.dp)
            ) {
                if (!isImageLoaded) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }

                AsyncImage(
                    model = movie.posterLarge,
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp)), // Rounded corners with radius of 16.dp
                    contentScale = ContentScale.Crop, // To crop the image and fit inside the rounded corners
                    onSuccess = {
                        isImageLoaded = true
                    }
                )
            }



            // Title with shimmer effect while loading
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp) // Fixed height for the shimmer placeholder
                    .padding(bottom = 8.dp)
            ) {
                if (isDataLoaded) {
                    Text(
                        text = movie.title,
                        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
            }

            // Year and Rating Row with shimmer effect while loading
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(200.dp) // Fixed width for the shimmer placeholder
                        .height(20.dp)
                ) {
                    if (isDataLoaded) {
                        // Check if release_date is null or not
                        val releaseDateText = movie.release_date?.let {
                            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                            dateFormat.format(it) // Format Date to String
                        } ?: "Release: Not available" // Default text if release_date is null

                        Text(
                            text = "Release : $releaseDateText",
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        ShimmerEffect(modifier = Modifier.fillMaxSize())
                    }
                }

                // Rating
                Box(
                    modifier = Modifier
                        .width(120.dp) // Fixed width for the shimmer placeholder
                        .height(20.dp)
                ) {
                    if (isDataLoaded) {
                        Text(
                            text = "Rating: ${movie.user_rating} / 10",
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        ShimmerEffect(modifier = Modifier.fillMaxSize())
                    }
                }
            }

            // Description with shimmer effect while loading
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight() // Allow height to wrap based on content size
            ) {
                if (isDataLoaded) {
                    Text(
                        text = movie.plot_overview,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                } else {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

