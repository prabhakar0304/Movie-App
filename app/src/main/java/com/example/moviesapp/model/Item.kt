package com.example.moviesapp.model

data class Item(
    val id: Int,
    val title: String,
    val year: Int,
    val imdb_id: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val type: String
)

