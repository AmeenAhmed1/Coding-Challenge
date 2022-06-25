package com.ameen.trianglzchallenge.data.model

data class MoviesResponse(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)