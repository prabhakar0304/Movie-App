package com.example.moviesapp

import android.os.Bundle
import android.util.Log
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MoviesAppTheme {
                // Collect the selectedScreen StateFlow as State
                val selectedScreen by homeViewModel.selectedScreen.collectAsState()
//                Log.d("demo", "demo")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeView(
                        modifier = Modifier.padding(innerPadding),
                        selectedScreen = selectedScreen,
                        // Pass the selected screen to toggleScreen
                        onToggleScreen = { homeViewModel.toggleScreen(it) }
                    )
                }
            }
        }
    }
}