package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.moviesapp.ui.theme.MoviesAppTheme
import com.example.moviesapp.view.HomeView
import com.example.moviesapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

// This annotation marks the activity as a Hilt injection entry point.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Using Hilt to provide an instance of HomeViewModel scoped to this activity.
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for this activity.
        enableEdgeToEdge()

        // Sets the content view with Jetpack Compose.
        setContent {
            // Applying the custom app theme.
            MoviesAppTheme {
                // Collects the current selectedScreen value from the ViewModel's StateFlow.
                val selectedScreen by homeViewModel.selectedScreen.collectAsState()

                // Setting up the UI layout using a Scaffold to manage the screen structure.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Displays the HomeView composable and passes the necessary data and callbacks.
                    HomeView(
                        modifier = Modifier.padding(innerPadding),
                        selectedScreen = selectedScreen,
                        // Callback to handle screen toggle actions from the ViewModel.
                        onToggleScreen = { homeViewModel.toggleScreen(it) }
                    )
                }
            }
        }
    }
}