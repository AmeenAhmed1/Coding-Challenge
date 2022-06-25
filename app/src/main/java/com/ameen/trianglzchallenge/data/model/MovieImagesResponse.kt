package com.ameen.trianglzchallenge.data.model

data class MovieImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)