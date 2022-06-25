package com.ameen.trianglzchallenge.data.mapper

import com.ameen.trianglzchallenge.data.model.MovieImagesResponse
import com.ameen.trianglzchallenge.data.model.MovieModel
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.model.MovieImages

class DataModelMapper {


    fun movieDataResponseToViewState(movieDataList: List<MovieModel>): List<MovieData> {
        val movieList = mutableListOf<MovieData>()
        for (movie in movieDataList) {
            movieList.add(
                MovieData(
                    genre_ids = movie.genre_ids,
                    id = movie.id,
                    overview = movie.overview,
                    poster_path = movie.poster_path ?: "",
                    backdrop_path = movie.backdrop_path ?: "",
                    release_date = movie.release_date,
                    title = movie.title,
                    vote_average = movie.vote_average
                )
            )
        }
        return movieList.toList()
    }


    fun movieImagesResponseToViewState(movieImagesResponse: MovieImagesResponse): MovieImages {
        val imageLinks = mutableListOf<String>()
        for (image in movieImagesResponse.backdrops) {
            imageLinks.add(image.file_path)
        }
        return MovieImages(imageLinks)
    }
}