package com.example.moviesapp.model

import java.util.Date

data class ItemDetail(
    val id: Int,
    val title: String,
    val year: Int,
    val plot_overview: String,
    val posterLarge: String,
    val user_rating: Double,
    val release_date : Date
)
