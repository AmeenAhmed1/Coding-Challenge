package com.ameen.trianglzchallenge.data.remote

import com.ameen.trianglzchallenge.core.util.ApiEndPoints
import com.ameen.trianglzchallenge.data.model.MovieImagesResponse
import com.ameen.trianglzchallenge.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiEndPoints.TOP_MOVIES_ENDPOINT)
    suspend fun getTopRatedMoves(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int = 1
    ): Response<MoviesResponse>

    @GET(ApiEndPoints.POPULAR_MOVIE_ENDPOINT)
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int = 1
    ): Response<MoviesResponse>

    @GET(ApiEndPoints.MOVIE_IMAGES_ENDPOINT)
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieImagesResponse>

}