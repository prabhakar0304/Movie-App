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
    selectedScreen: HomeViewModel.Screen, // Holds the current selected screen
    onToggleScreen: (HomeViewModel.Screen) -> Unit // Function to toggle between screens
) {
    Column(
        modifier = modifier.fillMaxSize(), // Ensure the Column takes full available space
        verticalArrangement = Arrangement.Top, // Align content to the top
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        // Row of Toggle Buttons for Movies and TV Shows
        Row(modifier = Modifier.padding(16.dp)) {

            // Movies Button
            Button(
                onClick = { onToggleScreen(HomeViewModel.Screen.Movies) }, // Set selected screen to Movies
                modifier = Modifier.weight(1f), // Makes the button take equal width with other buttons
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedScreen == HomeViewModel.Screen.Movies) Color(0xFFA020F0) // Purple color for selected button
                    else Color(0xFFA4A7AB), // Dark gray for unselected button
                    contentColor = Color.White // White text color
                )
            ) {
                Text("Movies") // Button label for Movies
            }

            Spacer(modifier = Modifier.width(16.dp)) // Small space between buttons

            // TV Shows Button
            Button(
                onClick = { onToggleScreen(HomeViewModel.Screen.TVShows) }, // Set selected screen to TV Shows
                modifier = Modifier.weight(1f), // Makes the button take equal width with other buttons
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedScreen == HomeViewModel.Screen.TVShows) Color(0xFFA020F0) // Purple color for selected button
                    else Color(0xFFA4A7AB), // Dark gray for unselected button
                    contentColor = Color.White // White text color
                )
            ) {
                Text("TV Shows") // Button label for TV Shows
            }
        }

        // Display content based on the currently selected screen
        when (selectedScreen) {
            HomeViewModel.Screen.TVShows -> TvShowsScreen() // Show TV Shows screen if selected
            HomeViewModel.Screen.Movies -> MoviesScreen() // Show Movies screen if selected
        }
    }
}