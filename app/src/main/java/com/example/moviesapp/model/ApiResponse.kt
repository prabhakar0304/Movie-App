package com.example.moviesapp.model

data class ApiResponse(
    val titles: List<Item>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)