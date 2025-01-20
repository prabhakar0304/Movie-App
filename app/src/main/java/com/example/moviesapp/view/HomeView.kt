package com.example.moviesapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.viewmodel.HomeViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    selectedScreen: HomeViewModel.Screen,
    onToggleScreen: (HomeViewModel.Screen) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Toggle Buttons
        Row(modifier = Modifier.padding(16.dp)) {
            // Movies Button
            Button(
                onClick = { onToggleScreen(HomeViewModel.Screen.Movies) },
                modifier = Modifier.weight(1f), // Makes the button take equal width
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedScreen == HomeViewModel.Screen.Movies) Color(0xFFA020F0) // Purple color
                    else Color(0xFFA4A7AB), // Dark gray
                    contentColor = Color.White
                )
            ) {
                Text("Movies")
            }

            Spacer(modifier = Modifier.width(16.dp)) // Small space between buttons

            // TV Shows Button
            Button(
                onClick = { onToggleScreen(HomeViewModel.Screen.TVShows) },
                modifier = Modifier.weight(1f), // Makes the button take equal width
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedScreen == HomeViewModel.Screen.TVShows) Color(0xFFA020F0) // Purple color
                    else Color(0xFFA4A7AB), // Dark gray
                    contentColor = Color.White
                )
            ) {
                Text("TV Shows")
            }
        }

        // Content Based on Selected Screen
        when (selectedScreen) {
            HomeViewModel.Screen.TVShows-> TvShowsScreen()
            HomeViewModel.Screen.Movies -> MoviesScreen()
        }
    }
}